> [!IMPORTANT]  
> This code is moved into [nva-publication-api](https://github.com/BIBSYSDEV/nva-publication-api)

# NVA Datamodel

This repository contains the ontology of NVA.

## When updating the model

### The ontology model

When updating the model, it is important that the ontology-file is kept up-to-date and compiles. To make sure it compiles one can use `raptor`, 
which can generate RDF triples by parsing the ontology-file.
```
brew install raptor
```
```
rapper -i turtle path-to-ontology-file
```

### PublicationResponse

When adding new fields at the root of `Publication`, the `PubblicationResponse` has to be updated to prevent them being removed in publication-api-responses.

When adding fields at deeper levels, this is not necessary. 
