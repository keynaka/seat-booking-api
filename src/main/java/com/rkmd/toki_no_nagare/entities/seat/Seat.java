package com.rkmd.toki_no_nagare.entities.seat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rkmd.toki_no_nagare.entities.booking.Booking;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@IdClass(SeatId.class)
@Table(name="seat")
public class Seat {
    @Id
    @Column(name = "row", nullable = false)
    private Long row;

    @Id
    @Column(name = "column", nullable = false)
    private Long column;

    private BigDecimal price;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "sector", nullable = false)
    private SeatSector sector;

    @Column(name = "auxiliar_column")
    private Integer auxiliarColumn;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SeatStatus status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Long getRow() {
        return row;
    }

    public void setRow(Long row) {
        this.row = row;
    }

    public Long getColumn() {
        return column;
    }

    public void setColumn(Long column) {
        this.column = column;
    }

    public SeatSector getSector() {
        return sector;
    }

    public void setSector(SeatSector sector) {
        this.sector = sector;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Integer getAuxiliarColumn() {
        return auxiliarColumn;
    }

    public void setAuxiliarColumn(Integer auxiliarColumn) {
        this.auxiliarColumn = auxiliarColumn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
