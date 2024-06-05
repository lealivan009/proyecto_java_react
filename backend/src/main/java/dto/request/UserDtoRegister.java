package dto.request;

public record UserDtoRegister(
    String email,
    String password,
    String photo,
    String name,
    String lastname,
    String dni,
    String medicalInsurance,
    int age
) {}
