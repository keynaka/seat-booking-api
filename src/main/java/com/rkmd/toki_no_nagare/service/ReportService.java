package com.rkmd.toki_no_nagare.service;

import com.rkmd.toki_no_nagare.entities.booking.Booking;
import com.rkmd.toki_no_nagare.entities.booking.BookingStatus;
import com.rkmd.toki_no_nagare.entities.payment.PaymentMethod;
import com.rkmd.toki_no_nagare.entities.seat.Seat;
import com.rkmd.toki_no_nagare.service.expiration.ExpirationService;
import com.rkmd.toki_no_nagare.service.expiration.ExpirationServiceFactory;
import com.rkmd.toki_no_nagare.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReportService {
    @Autowired
    private ExpirationServiceFactory expirationServiceFactory;

    public static final String PRE_EXPIRED = "PRE-EXPIRED";

    public String formatTitle(Booking booking) {
        String sector = "";
        Long row = 0l;
        String seats = "";

        List<Seat> sortedSeats = booking.getSeats().stream().sorted((a, b) -> a.getColumn().compareTo(b.getColumn())).collect(Collectors.toList());
        for (Seat seat : sortedSeats) {
            sector = seat.getSector().name();
            row = seat.getRow();
            seats = String.format("%s %s", seats, seat.getColumn().toString());
        }

        String result =  String.format("Sector %s - Fila %s - Asiento %s - $ %s", sector, row, seats, booking.getPayment().getAmount().toString());

        if (isPreExpiredBooking(booking)) {
            //Long adminExtraDays = expirationServiceFactory.getExpirationService(booking.getPayment().getPaymentMethod()).adminExpireExtraDays(); TODO: ROLLBACK TO THIS PRODUCTIVE
            Long adminExtraDays = expirationServiceFactory.getExpirationService(PaymentMethod.MERCADO_PAGO).adminExpireExtraDays();
            result = String.format("(EXPIRES: %s) %s ", Tools.formatArgentinianDate(booking.getExpirationDate().plusDays(adminExtraDays)), result);
        }

        return result;
    }

    public String calculateStatus(Booking booking) {
        return isPreExpiredBooking(booking) ? PRE_EXPIRED : booking.getStatus().name();
    }

    private boolean isPreExpiredBooking(Booking booking) {
        // ExpirationService expirationService = expirationServiceFactory.getExpirationService(booking.getPayment().getPaymentMethod()); TODO: ROLLBACK TO THIS PRODUCTIVE
        ExpirationService expirationService = expirationServiceFactory.getExpirationService(PaymentMethod.MERCADO_PAGO);

        return booking.getStatus().equals(BookingStatus.PENDING) &&
                expirationService.isExpiredForClient(booking.getExpirationDate()) &&
                !expirationService.isExpiredForAdmin(booking.getExpirationDate());
    }
}