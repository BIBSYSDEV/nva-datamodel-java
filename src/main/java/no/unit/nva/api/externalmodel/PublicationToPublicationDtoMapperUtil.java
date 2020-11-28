package no.unit.nva.api.externalmodel;

import no.unit.nva.model.Publication;
import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.LinkedContext;
import no.unit.nva.model.contexttypes.PublicationContext;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.instancetypes.TextbookContent;
import no.unit.nva.model.instancetypes.journal.JournalContent;
import no.unit.nva.model.instancetypes.musicalcontent.MusicNotation;
import no.unit.nva.model.pages.Pages;

import java.net.URI;
import java.util.List;

public class PublicationToPublicationDtoMapperUtil {
    protected static PublicationDto toPublicationDto(Publication publication) {
        var entityDescription = publication.getEntityDescription();
        var reference = entityDescription.getReference();
        PublicationInstance<? extends Pages> publicationInstance = reference.getPublicationInstance();
        var type = publicationInstance.getClass().getSimpleName();
        var identifier = publication.getIdentifier();
        var doi = publication.getDoi();
        var owner = new Owner(publication.getOwner(), publication.getPublisher());
        var status = publication.getStatus();
        var createdDate = publication.getCreatedDate();
        var modifiedDate = publication.getModifiedDate();
        var doiRequest = publication.getDoiRequest();
        var project = publication.getProject();
        var files = publication.getFileSet();
        var handle = publication.getHandle();
        var indexedDate = publication.getIndexedDate();
        var link = publication.getLink();
        var publishedDate = publication.getPublishedDate();
        var metadata = createMetadata(publication);
        return new PublicationDto(createdDate, doi, doiRequest, files, handle, identifier, indexedDate, link, metadata,
                modifiedDate, owner, project, publishedDate, status, type);
    }


    private static Metadata createMetadata(Publication publication) {
        var entityDescription = publication.getEntityDescription();
        var reference = entityDescription.getReference();
        PublicationInstance<? extends Pages> publicationInstance = reference.getPublicationInstance();

        var referenceDto = createReferenceDto(publication);

        return new Metadata.Builder()
                .withAbstrakt(entityDescription.getAbstract())
                .withAlternativeTitle(entityDescription.getAlternativeTitles())
                .withContributors(entityDescription.getContributors())
                .withDescription(entityDescription.getDescription())
                .withLanguage(entityDescription.getLanguage())
                .withMainTitle(entityDescription.getMainTitle())
                .withMetadataSource(entityDescription.getMetadataSource())
                .withNpiSubjectHeading(entityDescription.getNpiSubjectHeading())
                .withTag(entityDescription.getTags())
                .withTextbook(extractTextbookFromPublicationInstance(publicationInstance))
                .withReference(referenceDto)
                .build();
    }

    private static Reference createReferenceDto(Publication publication) {
        var entityDescription = publication.getEntityDescription();
        var reference = entityDescription.getReference();
        PublicationInstance<? extends Pages> publicationInstance = reference.getPublicationInstance();
        var publicationContext = reference.getPublicationContext();

        return new Reference.Builder()
                .withArticleNumber(extractArticleInstance(publicationInstance))
                .withDate(entityDescription.getDate())
                .withIsbn(extractIsbnFromPublicationContext(publicationContext))
                .withIsmn(extractIsmnFromPublicationInstance(publicationInstance))
                .withIssue(extractIssueFromPublicationInstance(publicationInstance))
                .withLinkedContext(extractLinkedContextFromPublicationContext(publicationContext))
                .withOriginalDoi(reference.getDoi())
                .withPages(publicationInstance.getPages())
                .withPeerReviewed(publicationInstance.isPeerReviewed())
                .withPublisher(getOriginalPublisher(publicationContext))
                .withSeries(extractSeriesFromPublicationContext(publicationContext))
                .withVolume(extractVolumeFromPublicationInstance(publicationInstance))
                .build();
    }

    private static String extractIsmnFromPublicationInstance(PublicationInstance<? extends Pages> publicationInstance) {
        return publicationInstance instanceof MusicNotation
                ? ((MusicNotation) publicationInstance).getIsmn() : null;
    }

    private static List<String> extractIsbnFromPublicationContext(PublicationContext publicationContext) {
        return publicationContext instanceof Book ? ((Book) publicationContext).getIsbnList() : null;
    }

    private static String extractVolumeFromPublicationInstance(
            PublicationInstance<? extends Pages> publicationInstance) {
        return publicationInstance instanceof JournalContent
                ? ((JournalContent) publicationInstance).getVolume() : null;
    }

    private static boolean extractTextbookFromPublicationInstance(
            PublicationInstance<? extends Pages> publicationInstance) {
        return publicationInstance instanceof TextbookContent
                && ((TextbookContent) publicationInstance).isTextbookContent();
    }

    private static SeriesDto extractSeriesFromPublicationContext(PublicationContext publicationContext) {
        return publicationContext instanceof Book
                ? getSeriesInformation((Book) publicationContext) : null;
    }

    private static URI extractLinkedContextFromPublicationContext(PublicationContext publicationContext) {
        return publicationContext instanceof LinkedContext
                ? ((LinkedContext) publicationContext).getLinkedContext() : null;
    }

    private static String extractIssueFromPublicationInstance(
            PublicationInstance<? extends Pages> publicationInstance) {
        return publicationInstance instanceof JournalContent
                ? ((JournalContent) publicationInstance).getIssue() : null;
    }

    private static String extractArticleInstance(PublicationInstance<? extends Pages> publicationInstance) {
        return publicationInstance instanceof JournalContent
                ? ((JournalContent) publicationInstance).getArticleNumber() : null;
    }

    private static SeriesDto getSeriesInformation(Book publicationContext) {
        var seriesTitle = publicationContext.getSeriesTitle();
        var seriesNumber = publicationContext.getSeriesNumber();
        return new SeriesDto(seriesTitle, seriesNumber);
    }

    private static OriginalPublishingChannelDto getOriginalPublisher(PublicationContext publicationContext) {
        if (publicationContext instanceof LinkedContext) {
            return null;
        } else {
            return new OriginalPublishingChannelDto(publicationContext);
        }
    }
}
