package com.training.axonsample1.common.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InactivateAccountCommand {

    @TargetAggregateIdentifier
    private String id;//-> ref to account aggregate



}
