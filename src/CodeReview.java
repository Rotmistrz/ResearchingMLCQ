import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvBindByName;

public class CodeReview {
    @CsvBindByName(column = "id")
    public int reviewID;

    @CsvBindByName(column = "reviewer_id")
    public int reviewerID;

    @CsvBindByName(column = "sample_id")
    public int sampleID;

    @CsvBindByName(column = "smell")
    public String codeSmell;

    @CsvBindByName(column = "severity")
    public String severity;

    @CsvBindByName(column = "review_timestamp")
    public String timestamp;

    @CsvBindByName(column = "type")
    public String type;

    @CsvBindByName(column = "code_name")
    public String codeName;

    @CsvBindByName(column = "repository")
    public String repository;

    @CsvBindByName(column = "commit_hash")
    public String commitHash;

    @CsvBindByName(column = "path")
    public String path;

    @CsvBindByName(column = "start_line")
    public int startLine;

    @CsvBindByName(column = "end_line")
    public int endLine;

    @CsvBindByName(column = "link")
    public String link;

    @CsvBindByName(column = "is_from_industry_relevant_project")
    public String isFromIndustryRelevantProject;

    //  getters, setters, toString
}
