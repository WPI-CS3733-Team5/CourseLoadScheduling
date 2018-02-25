package org.dselent.scheduling.server.dto;

import java.util.Objects;

/**
 * DTO = Data Transfer Object
 * Used to package/wrap several variables into a single object
 * Uses the Builder pattern for object instantiation
 *
 * @author dselent
 *
 */
public class CreateSectionDto
{
	//Section Information
    private final Integer sectionNumber;
    private final String sectionType;
    private final Integer instructorInfoId;
    private final String location;
    private final Boolean deleted;
    private final Integer courseInfoId;
    private final Integer calendarInfoId;
    
    //Calendar Information
    private final Integer year;
	private final String semester;
    private final String days;
    private final Integer startTime;
    private final Integer endTime;
    private final Integer term;

    public Integer getSectionNumber() {
    	return sectionNumber;
    }
    public String getSectionType() {
    	return sectionType;
    }
    public Integer getInstructorInfoId() {
    	return instructorInfoId;
    }
    public String getLocation() {
    	return location;
    }
    public Boolean getDeleted() {
    	return deleted;
    }
    public Integer getCourseInfoId() {
    	return courseInfoId;
    }
    public Integer getCalendarInfoId() {
    	return calendarInfoId;
    }
    public Integer getYear() {
		return year;
	}
	public String getSemester() {
		return semester;
	}
	public String getDays() {
		return days;
	}
	public Integer getStartTime() {
		return startTime;
	}
	public Integer getEndTime() {
		return endTime;
	}
	public Integer getTerm() {
		return term;
	}
    
    private CreateSectionDto(Builder builder){
        this.sectionNumber = builder.sectionNumber;
        this.sectionType = builder.sectionType;
        this.instructorInfoId = builder.instructorInfoId;
        this.location = builder.location;
        this.deleted = builder.deleted;
        this.courseInfoId = builder.courseInfoId;
        this.calendarInfoId = builder.calendarInfoId;
        this.year = builder.year;
        this.semester = builder.semester;
        this.days = builder.days;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.term = builder.term;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "CreateSectionDto{" +
                "section_number=" + sectionNumber +
                ", section_type='" + sectionType + '\'' +
                ", instructor_info_id=" + instructorInfoId +
                ", location='" + location + '\'' +
                ", deleted=" + deleted +
                ", course_info_id=" + courseInfoId +
                ", calendar_info_id=" + calendarInfoId +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CreateSectionDto that = (CreateSectionDto) object;
        return java.util.Objects.equals(sectionNumber, that.sectionNumber) &&
                java.util.Objects.equals(sectionType, that.sectionType) &&
                java.util.Objects.equals(instructorInfoId, that.instructorInfoId) &&
                java.util.Objects.equals(location, that.location) &&
                java.util.Objects.equals(deleted, that.deleted) &&
                java.util.Objects.equals(courseInfoId, that.courseInfoId) &&
                java.util.Objects.equals(calendarInfoId, that.calendarInfoId);
    }

    public int hashCode() {

        return Objects.hash(super.hashCode(), sectionNumber, sectionType, instructorInfoId, location, deleted, courseInfoId, calendarInfoId);
    }
    public static Builder builder() {
    	return new Builder();
    }
    public static final class Builder {
    	//Section Info
        private Integer sectionNumber;
        private String sectionType;
        private Integer instructorInfoId;
        private String location;
        private Boolean deleted;
        private Integer courseInfoId;
        private Integer calendarInfoId;
        
        //Calendar Info
        private Integer year;
    	private String semester;
        private String days;
        private Integer startTime;
        private Integer endTime;
        private Integer term;
        

        public Builder() {
        }
                
        public Builder withSectionNumber(Integer sectionNumber) {
            this.sectionNumber = sectionNumber;
            return this;
        }

        public Builder withSectionType(String sectionType) {
            this.sectionType = sectionType;
            return this;
        }

        public Builder withInstructorInfoId(Integer instructorInfoId) {
            this.instructorInfoId = instructorInfoId;
            return this;
        }

        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder withDeleted(Boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Builder withCourseInfoId(Integer courseInfoId) {
            this.courseInfoId = courseInfoId;
            return this;
        }

        public Builder withCalendarInfoId(Integer calendarInfoId) {
            this.calendarInfoId = calendarInfoId;
            return this;
        }

        public CreateSectionDto build() {
            return new CreateSectionDto(this);
        }
        
        
        public Builder withYear(Integer year) {
        	this.year = year;
        	return this;
        }
        
        public Builder withSemester(String semester) {
        	this.semester = semester;
        	return this;
        }
        
        public Builder withDays(String days) {
        	this.days = days;
        	return this;
        }
        
        public Builder withStartTime(Integer startTime) {
        	this.startTime = startTime;
        	return this;
        }
        
        public Builder withEndTime(Integer endTime) {
        	this.endTime = endTime;
        	return this;
        }
        
        public Builder withTerm(Integer term) {
        	this.term = term;
        	return this;
        }
    }
}
