package mn.data.mysql.dtos;

import javax.validation.constraints.NotBlank;

import io.micronaut.core.annotation.Introspected;
import lombok.Builder;

@Builder
@Introspected
public class AuthorDto {

    @NotBlank
    private String name;
    @NotBlank
    private Integer birthYear;

    public AuthorDto() {}

    public AuthorDto(String name, Integer birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }
}
