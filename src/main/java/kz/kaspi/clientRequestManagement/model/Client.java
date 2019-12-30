package kz.kaspi.clientRequestManagement.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue()
    long id;
    @NotNull
    String phone;
    @NotNull
    String fio;
    @NotNull
    String organization;
    @NotNull
    String bin;
    @NotNull
    String abbr;
    @NotNull
    int hashCode;

}

