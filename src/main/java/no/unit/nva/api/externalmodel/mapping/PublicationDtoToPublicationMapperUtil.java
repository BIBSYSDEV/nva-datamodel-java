package no.unit.nva.api.externalmodel.mapping;

import no.unit.nva.api.externalmodel.PublicationDto;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.Publication;
import no.unit.nva.model.Reference;

public class PublicationDtoToPublicationMapperUtil {

    /**
     * Takes a PublicationDto and returns a Publication.
     * @param publicationDto A publication DTO.
     * @return Publication.
     * @throws Exception If an ISBN, ISMN, or ISSN is invalid.
     */
    public static Publication toPublication(PublicationDto publicationDto) throws Exception {
        var metadata = publicationDto.getMetadata();
        var publicationInstance = MetadataToPublicationInstanceMapperUtil
                .getPublicationInstanceByName(publicationDto.getType(), metadata);
        var publicationContext = MetadataToPublicationContextObjectMapper
                .getPublicationContext(publicationDto.getType(), metadata);
        var reference = new Reference.Builder()
                .withDoi(metadata.getReference().getOriginalDoi())
                .withPublishingContext(publicationContext)
                .withPublicationInstance(publicationInstance)
                .build();

        var entityDescription = new EntityDescription.Builder()
                .withDescription(metadata.getDescription())
                .withAbstract(metadata.getAbstract())
                .withAlternativeTitles(metadata.getAlternativeTitle())
                .withContributors(metadata.getContributors())
                .withDate(metadata.getReference().getDate())
                .withLanguage(metadata.getLanguage())
                .withMainTitle(metadata.getMainTitle())
                .withMetadataSource(metadata.getMetadataSource())
                .withNpiSubjectHeading(metadata.getNpiSubjectHeading())
                .withReference(reference)
                .withTags(metadata.getTag())
                .build();

        return new Publication.Builder()
                .withIdentifier(publicationDto.getIdentifier())
                .withCreatedDate(publicationDto.getCreatedDate())
                .withDoi(publicationDto.getDoi())
                .withDoiRequest(publicationDto.getDoiRequest())
                .withEntityDescription(entityDescription)
                .withFileSet(publicationDto.getFiles())
                .withHandle(publicationDto.getHandle())
                .withModifiedDate(publicationDto.getModifiedDate())
                .withIndexedDate(publicationDto.getIndexedDate())
                .withLink(publicationDto.getLink())
                .withOwner(publicationDto.getOwner().getIdentity())
                .withProject(publicationDto.getProject())
                .withPublishedDate(publicationDto.getPublishedDate())
                .withPublisher(publicationDto.getOwner().getInstitution())
                .withStatus(publicationDto.getStatus())
                .build();
    }
}
