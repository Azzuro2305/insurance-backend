package insurance.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID agentID;

    private String agentName;
    private String agentLicense;
    private LocalDate agentDOB;
    private String agentPassword;
    private String agentType;
//    private UUID createdUserID;
//    private UUID updatedUserID;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;

    @OneToMany(mappedBy = "agent")
    private List<OutboundProposal> outboundProposal;

}
