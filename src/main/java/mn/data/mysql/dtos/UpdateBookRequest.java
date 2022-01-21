package mn.data.mysql.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.Nullable;
import lombok.*;
import mn.data.mysql.domain.Author;
import mn.data.mysql.enums.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor
public class UpdateBookRequest {

    @NotNull
    private long id;
    @Nullable
    private String title;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp pubDate;//Todo Date
    @NotNull
    private Author author;
    @Nullable
    private String isbn;
    @NotNull
    private Category category;
    @Nullable
    private Float sellPrice;

}