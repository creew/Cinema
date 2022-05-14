package edu.school21.cinema.models.dto;

public class FilmDto {
    private Integer id;

    private String title;

    private Integer yearOfRelease;

    private String restrictions;

    private String description;

    private String posterPath;

    private String posterFilename;

    private String posterContentType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterFilename() {
        return posterFilename;
    }

    public void setPosterFilename(String posterFilename) {
        this.posterFilename = posterFilename;
    }

    public String getPosterContentType() {
        return posterContentType;
    }

    public void setPosterContentType(String posterContentType) {
        this.posterContentType = posterContentType;
    }
}
