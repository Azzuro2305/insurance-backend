package insurance.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutboundProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID outboundProposalID;
    private LocalDate submittedDate;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private LocalDate estimateDepartureDate;
    private String journeyFrom;
    private String journeyTo;
    private double rate;
    private int coveragePlan;
    private String packages;
//    private UUID insuredPersonID;
//    private UUID agentID;
//    private UUID createdUserID;
//    private UUID updatedUserID;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;

    @ManyToOne
    @JoinColumn(name = "InsuredPerson_id")
    private InsuredPerson insuredPerson;

    @ManyToOne
    @JoinColumn(name = "Agent_id")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "PremiumRate_id")
    private PremiumRate premiumRate;


}
