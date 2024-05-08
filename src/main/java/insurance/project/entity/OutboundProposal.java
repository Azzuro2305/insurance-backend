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
    private UUID id;
    private LocalDate submittedDate;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private LocalDate estimateDepartureDate;
    private String journeyFrom;  // default value will be given from frontend side (MYANMAR)
    private String journeyTo;
    private String destinationCountry;
    private double rate;
    private int coveragePlan;
    private String packages;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;

//    @ManyToOne
//    @JoinColumn(name = "insured_person_id")
//    private InsuredPerson insuredPerson;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "agent_id")
//    private Agent agent;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "premium_rate_id")
//    private PremiumRate premiumRate;
//
//    @OneToOne(mappedBy = "outboundProposal", fetch = FetchType.LAZY)
//    private Child child;
//
//    @OneToOne(mappedBy = "outboundProposal", fetch = FetchType.LAZY)
//    private Beneficiary beneficiary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "premium_rate_id")
    private PremiumRate premiumRate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "insured_person_id")
//    private InsuredPerson insuredPerson;
}
