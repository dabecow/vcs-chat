package edu.oreluniver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Class description
 */
@AllArgsConstructor
@NoArgsConstructor
public class StateDto {

    @Getter
    private String stateName;

    @Getter
    private UUID id;

    @Getter
    private String branchName;
}
