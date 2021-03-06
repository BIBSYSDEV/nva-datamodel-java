package no.unit.nva.model;

import static no.unit.nva.model.DoiRequestStatus.APPROVED;
import static no.unit.nva.model.DoiRequestStatus.REJECTED;
import static no.unit.nva.model.DoiRequestStatus.REQUESTED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import java.io.FileInputStream;
import java.io.IOException;
import no.unit.nva.BuildConfig;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.util.ContextUtil;
import no.unit.nva.model.util.PublicationGenerator;
import nva.commons.core.Environment;
import org.junit.jupiter.api.Test;

public class PublicationTest {

    public static final String PUBLICATION_CONTEXT_JSON = "src/main/resources/publicationContext.json";
    public static final String PUBLICATION_FRAME_JSON = "src/main/resources/publicationFrame.json";

    protected static final ObjectMapper objectMapper = nva.commons.core.JsonUtils.objectMapper;


    @Test
    public void updatingDoiStatusSuccessfullyChangesToValidNewDoiStatus()
        throws InvalidIssnException, MalformedContributorException {
        var publication = generatePublicationWithRejectedDoiRequestStatus();

        var validRequestedChange = APPROVED;
        // ensure we have different status before trying to update
        assertThat(publication.getDoiRequest().getStatus(), not(equalTo(validRequestedChange)));

        publication.updateDoiRequestStatus(validRequestedChange);
        assertThat(publication.getDoiRequest().getStatus(), is(equalTo(validRequestedChange)));
    }

    @Test
    public void updatingDoiRequestWithInvalidRequestedStatusChangeThenThrowsIllegalArgumentException()
        throws InvalidIssnException, MalformedContributorException {
        var publication = generatePublicationWithRejectedDoiRequestStatus();

        var actualMessage = assertThrows(IllegalArgumentException.class,
            () -> publication.updateDoiRequestStatus(REQUESTED))
                                .getMessage();

        assertThat(actualMessage, containsStringIgnoringCase("not allowed"));

        String statusInRequest = REQUESTED.toString();
        assertThat(actualMessage, containsStringIgnoringCase(statusInRequest));

        String initialStatus = REJECTED.toString();
        assertThat(actualMessage, containsStringIgnoringCase(initialStatus));
    }

    @Test
    public void updatingDoiRequestStatusWithoutDoiRequestThrowsIllegalStateException()
        throws InvalidIssnException, MalformedContributorException {
        var publication = getPublicationWithoutDoiRequest();

        var actualException = assertThrows(IllegalStateException.class,
            () -> publication.updateDoiRequestStatus(REQUESTED));
        assertThat(actualException.getMessage(),
            is(equalTo(Publication.ERROR_MESSAGE_UPDATEDOIREQUEST_MISSING_DOIREQUEST)));
    }

    @Test
    public void getModelVersionReturnsModelVersionDefinedByGradle()
        throws InvalidIssnException, MalformedContributorException {
        Publication samplePublication = getPublicationWithoutDoiRequest();
        assertThat(samplePublication.getModelVersion(), is(equalTo(BuildConfig.MODEL_VERSION)));
    }

    protected JsonNode toPublicationWithContext(Publication publication) throws IOException {
        JsonNode document = objectMapper.readTree(objectMapper.writeValueAsString(publication));
        JsonNode context = objectMapper.readTree(new FileInputStream(PUBLICATION_CONTEXT_JSON));
        ContextUtil.injectContext(document, context);
        return document;
    }

    protected Object produceFramedPublication(JsonNode publicationWithContext) throws IOException {
        Object input = JsonUtils.fromString(objectMapper.writeValueAsString(publicationWithContext));
        Object frame = JsonUtils.fromInputStream(new FileInputStream(PUBLICATION_FRAME_JSON));
        JsonLdOptions options = new JsonLdOptions();
        options.setOmitGraph(true);
        options.setPruneBlankNodeIdentifiers(true);
        return JsonLdProcessor.frame(input, frame, options);
    }

    private Publication getPublicationWithoutDoiRequest() throws InvalidIssnException, MalformedContributorException {
        return PublicationGenerator.generateJournalArticlePublication().copy()
                   .withDoiRequest(null).build();
    }

    private Publication generatePublicationWithRejectedDoiRequestStatus()
        throws InvalidIssnException, MalformedContributorException {
        var doiRequest = PublicationGenerator.generateJournalArticlePublication().getDoiRequest();
        return PublicationGenerator.generateJournalArticlePublication()
                   .copy().withDoiRequest(doiRequest.copy().withStatus(REJECTED).build()).build();
    }
}
