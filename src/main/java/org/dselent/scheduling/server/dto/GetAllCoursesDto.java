package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * DTO = Data Transfer Object
 * Used to package/wrap several variables into a single object
 * Uses the Builder pattern for object instantiation
 *
 * @author dselent
 *
 */
public class GetAllCoursesDto
{
    private final String courseName;
    private final Integer requiredFrequencyPerTerm;
    private final Integer requiredFrequencyPerSemester;
    private final Integer requiredFrequencyPerYear;
    private final Float creditAmount;
    private final Boolean deleted;
    private final String department;
    private final Integer courseNumber;

    private GetAllCoursesDto(Builder builder){
        courseName = builder.courseName;
        requiredFrequencyPerTerm = builder.requiredFrequencyPerTerm;
        requiredFrequencyPerSemester = builder.requiredFrequencyPerSemester;
        requiredFrequencyPerYear = builder.requiredFrequencyPerYear;
        creditAmount = builder.creditAmount;
        deleted = builder.deleted;
        department = builder.department;
        courseNumber = builder.courseNumber;

        if(this.courseName == null)
        {
            throw new IllegalStateException("courseName cannot be null");
        }
        else if(this.requiredFrequencyPerTerm == null)
        {
            throw new IllegalStateException("requiredFrequencyPerTerm cannot be null");
        }
        else if(this.requiredFrequencyPerSemester == null)
        {
            throw new IllegalStateException("requiredFrequencyPerSemester cannot be null");
        }
        else if(this.requiredFrequencyPerYear == null)
        {
            throw new IllegalStateException("requiredFrequencyPerYear cannot be null");
        }
        else if(this.creditAmount == null)
        {
            throw new IllegalStateException("creditAmount cannot be null");
        }
        else if(this.deleted == null)
        {
            throw new IllegalStateException("deleted cannot be null");
        }
        else if(this.department == null)
        {
            throw new IllegalStateException("department cannot be null");
        }
        else if(this.courseNumber == null)
        {
            throw new IllegalStateException("courseNumber cannot be null");
        }
    }

    public static final class Builder {
        private String courseName;
        private Integer requiredFrequencyPerTerm;
        private Integer requiredFrequencyPerSemester;
        private Integer requiredFrequencyPerYear;
        private Float creditAmount;
        private Boolean deleted;
        private String department;
        private Integer courseNumber;

        private Builder() {
        }

        public static Builder aGetAllCoursesDto() {
            return new Builder();
        }

        public Builder withCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public Builder withRequiredFrequencyPerTerm(Integer requiredFrequencyPerTerm) {
            this.requiredFrequencyPerTerm = requiredFrequencyPerTerm;
            return this;
        }

        public Builder withRequiredFrequencyPerSemester(Integer requiredFrequencyPerSemester) {
            this.requiredFrequencyPerSemester = requiredFrequencyPerSemester;
            return this;
        }

        public Builder withRequiredFrequencyPerYear(Integer requiredFrequencyPerYear) {
            this.requiredFrequencyPerYear = requiredFrequencyPerYear;
            return this;
        }

        public Builder withCreditAmount(Float creditAmount) {
            this.creditAmount = creditAmount;
            return this;
        }

        public Builder withDeleted(Boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Builder withDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder withCourseNumber(Integer courseNumber) {
            this.courseNumber = courseNumber;
            return this;
        }

        public GetAllCoursesDto build() {
           return  new GetAllCoursesDto(this);
        }
    }
}



