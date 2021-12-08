package no.unit.nva;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.lang.reflect.Field;
import no.unit.nva.model.Publication;
import no.unit.nva.model.testing.PublicationGenerator;
import org.junit.jupiter.api.Test;

// Tests should be deleted when the associated deprecated field has been deleted.
public class MigrationTests {

    @Test
    void resourceOwnerShouldOverrideOwnerWhenResourceOwnersOwnerIsNotNull()
        throws NoSuchFieldException, IllegalAccessException {
        var publication = PublicationGenerator.randomPublication();

        //here we verify that actual field value is potentially different from what we want to show outside
        String ownerFieldValue = extractOwnerFieldValue(publication);
        assertThat(ownerFieldValue, is(not(equalTo(publication.getResourceOwner().getOwner()))));

        //here we verify that the getter returns the desired field.
        assertThat(publication.getOwner(), is(equalTo(publication.getResourceOwner().getOwner())));
    }

    @Test
    void resourceOwnerShouldOverrideOwnersValueWhenSerializing()
        throws JsonProcessingException, NoSuchFieldException, IllegalAccessException {
        var publication = PublicationGenerator.randomPublication();
        var json = dataModelObjectMapper.writeValueAsString(publication);
        var deserialized = dataModelObjectMapper.readValue(json, Publication.class);
        assertThat(extractOwnerFieldValue(deserialized), is(equalTo(publication.getResourceOwner().getOwner())));
        assertThat(deserialized.getOwner(), is(equalTo(publication.getResourceOwner().getOwner())));
    }

    @Test
    void shouldPreserveOwnerValueWhenResourceOwnerIsNotSet() throws NoSuchFieldException, IllegalAccessException {
        var publication = PublicationGenerator.randomPublication();
        publication.setResourceOwner(null);
        String ownerFieldValue = extractOwnerFieldValue(publication);
        assertThat(publication.getOwner(), is(equalTo(ownerFieldValue)));
    }

    @Test
    void deserializedVersionShouldBeEqualToOriginalVersion() throws JsonProcessingException {
        var publication = PublicationGenerator.randomPublication();
        var json = dataModelObjectMapper.writeValueAsString(publication);
        var deserialized = dataModelObjectMapper.readValue(json, Publication.class);
        assertThat(deserialized, is(equalTo(publication)));
    }

    @Test
    void shouldPreserveOwnerValueWhenSerializingAndResourceOwnerIsNotSet()
        throws JsonProcessingException, NoSuchFieldException, IllegalAccessException {
        var publication = PublicationGenerator.randomPublication();
        String actualOwnerField = extractOwnerFieldValue(publication);
        publication.setResourceOwner(null);

        var json = dataModelObjectMapper.writeValueAsString(publication);
        var deserialized = dataModelObjectMapper.readValue(json, Publication.class);

        assertThat(deserialized.getOwner(), is(equalTo(actualOwnerField)));
        assertThat(deserialized.getOwner(), is(not(nullValue())));
        assertThat(deserialized,is(equalTo(publication)));
    }

    private String extractOwnerFieldValue(Publication publication) throws NoSuchFieldException, IllegalAccessException {
        Field ownerField = publication.getClass().getDeclaredField("owner");
        ownerField.setAccessible(true);
        return (String) ownerField.get(publication);
    }
}
