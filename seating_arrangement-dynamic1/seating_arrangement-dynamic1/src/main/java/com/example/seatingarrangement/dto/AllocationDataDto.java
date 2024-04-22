package com.example.seatingarrangement.dto;

import com.example.seatingarrangement.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllocationDataDto {
    private Integer algorithmPref;
    private Type allocationType;
    private String[][] allocationLayout;
}
