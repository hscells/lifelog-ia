package hscells.LifelogIa.dto;

/**
 * Created by Harry Scells on 17/12/2015.
 */
public class ImageDto {

    private int id;
    private String name;
    private String data;
    private boolean annotated;

    private String annotation;
    private int chunk_id;

    private int annotator;

    public ImageDto(){}

    public ImageDto(int id, int chunk_id, String name, String data, boolean annotated) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.chunk_id = chunk_id;
        this.annotated = annotated;
    }

    public ImageDto(int id, String name, String data, boolean annotated, String annotation, int annotator) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.annotated = annotated;

        this.annotation = annotation;
        this.annotator = annotator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isAnnotated() {
        return annotated;
    }

    public void setAnnotated(boolean annotated) {
        this.annotated = annotated;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public int getAnnotator() {
        return annotator;
    }

    public void setAnnotator(int annotator) {
        this.annotator = annotator;
    }

    public int getChunk_id() {
        return chunk_id;
    }

    public void setChunk_id(int chunk_id) {
        this.chunk_id = chunk_id;
    }
}
