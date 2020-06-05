package net.demo.exploreca.domain;

/**
 * Enumeration of the region of California.
 */
public enum Region {
    CENTRAL_COAST("Central Coast"),
    SOUTHERN_CALIFORNIA("Southern California"),
    NORTHERN_CALIFORNIA("Northern California"),
    VARIES("Varies");

    private String label;

    private Region(String label) {
        this.label = label;
    }

    public static Region findByLabel(String byLabel) {
        for (Region region : Region.values()) {
            if (region.label.equalsIgnoreCase(byLabel))
                return region;
        }
        return null;
    }
}
