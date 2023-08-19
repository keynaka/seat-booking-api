package com.rkmd.toki_no_nagare.dto.Contact;

import com.rkmd.toki_no_nagare.entities.contact.PhoneType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos del contacto")
public class ContactDto {

  @Schema(
      name = "dni",
      description = "detail: Número de documento (sin puntos y sin espacios) ",
      example = "11325689")
  private Long dni;

  @Schema(
      name = "name",
      description = "detail: Nombre",
      example = "Cristian Pablo Rodrigo Alejandro")
  private String name;

  @Schema(
      name = "lastName",
      description = "detail: Apellido",
      example = "Higa")
  private String lastName;

  @Schema(
      name = "email",
      description = "detail: Dirección de correo electrónico",
      example = "direccion_de_mail@gmail.com")
  private String email;

  @Schema(
      name = "phone",
      description = "detail: Número de telefono fijo o celular",
      example = "1132533689")
  private String phone;

  @Schema(
      name = "phoneType",
      description = "detail: Tipo de telefono: fijo o celular")
  private PhoneType phoneType;

}
