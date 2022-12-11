package dateConverter;

public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private String value;

    Feature(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Feature getFeatureByValue(String value){
        if(value == null || value.length() == 0) return null;

        for (Feature feature: Feature.values()) {
            if(feature.value.equals(value)) return feature;
        }
        return null;
    }
}
