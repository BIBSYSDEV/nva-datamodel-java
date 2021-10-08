package no.unit.nva.model;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ACADEMIC_COORDINATOR("AcademicCoordinator"),
    ADVISOR("Advisor"),
    CONTACT_PERSON("ContactPerson"),
    CREATOR("Creator"),
    DATA_COLLECTOR("DataCollector"),
    DATA_CURATOR("DataCurator"),
    DATA_MANAGER("DataManager"),
    DISTRIBUTOR("Distributor"),
    EDITOR("Editor"),
    EDITORIAL_BOARD_MEMBER("EditorialBoardMember"),
    FUNDER("Funder"),
    HOSTING_INSTITUTION("HostingInstitution"),
    ILLUSTRATOR("Illustrator"),
    INTERVIEW_SUBJECT("InterviewSubject"),
    JOURNALIST("Journalist"),
    OTHER("Other"),
    PRODUCER("Producer"),
    PROGRAMME_LEADER("ProgrammeLeader"),
    PROGRAMME_PARTICIPANT("ProgrammeParticipant"),
    PROJECT_LEADER("ProjectLeader"),
    PROJECT_MANAGER("ProjectManager"),
    PROJECT_MEMBER("ProjectMember"),
    REGISTRATION_AGENCY("RegistrationAgency"),
    REGISTRATION_AUTHORITY("RegistrationAuthority"),
    RELATED_PERSON("RelatedPerson"),
    RESEARCHER("Researcher"),
    RESEARCH_GROUP("ResearchGroup"),
    RIGHTS_HOLDER("RightsHolder"),
    SPONSOR("Sponsor"),
    SUPERVISOR("Supervisor"),
    WORK_PACKAGE_LEADER("WorkPackageLeader");

    public static final String ERROR_MESSAGE_TEMPLATE = "%s not a valid Role, expected one of: %s";
    public static final String DELIMITER = ", ";
    private final String value;

    Role(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Lookup enum by value.
     *
     * @param value value
     * @return enum
     */
    public static Role lookup(String value) {
        return stream(values())
                .filter(nameType -> nameType.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        format(ERROR_MESSAGE_TEMPLATE, value, stream(Role.values())
                                .map(Role::toString).collect(joining(DELIMITER)))));
    }
}
