package com.example.demo.DtoModel;

import com.example.demo.entity.EvaluationRated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationRatedDto {
    private EvaluationRated evaluationRated;
}
