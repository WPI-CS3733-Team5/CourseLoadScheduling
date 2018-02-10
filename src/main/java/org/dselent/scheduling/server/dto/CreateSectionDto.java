package org.dselent.scheduling.server.dto;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import org.dselent.scheduling.server.requests.CreateSection;

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
public class CreateSectionDto
{
    private final Integer section_number;
    private final String section_type;
    private final Integer instructor_info_id;
    private final String location;
    private final Boolean deleted;
    private final Integer course_info_id;
    private final Integer calendar_info_id;

    private CreateSectionDto(Builder builder){
        this.section_number = builder.section_number;
        this.section_type = builder.section_type;
        this.instructor_info_id = builder.instructor_info_id;
        this.location = builder.location;
        this.deleted = builder.deleted;
        this.course_info_id = builder.course_info_id;
        this.calendar_info_id = builder.calendar_info_id;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "CreateSectionDto{" +
                "section_number=" + section_number +
                ", section_type='" + section_type + '\'' +
                ", instructor_info_id=" + instructor_info_id +
                ", location='" + location + '\'' +
                ", deleted=" + deleted +
                ", course_info_id=" + course_info_id +
                ", calendar_info_id=" + calendar_info_id +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CreateSectionDto that = (CreateSectionDto) object;
        return java.util.Objects.equals(section_number, that.section_number) &&
                java.util.Objects.equals(section_type, that.section_type) &&
                java.util.Objects.equals(instructor_info_id, that.instructor_info_id) &&
                java.util.Objects.equals(location, that.location) &&
                java.util.Objects.equals(deleted, that.deleted) &&
                java.util.Objects.equals(course_info_id, that.course_info_id) &&
                java.util.Objects.equals(calendar_info_id, that.calendar_info_id);
    }

    public int hashCode() {

        return Objects.hash(super.hashCode(), section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id);
    }

    public static final class Builder {
        private Integer section_number;
        private String section_type;
        private Integer instructor_info_id;
        private String location;
        private Boolean deleted;
        private Integer course_info_id;
        private Integer calendar_info_id;

        public Builder() {
        }

        public Builder withSection_number(Integer section_number) {
            this.section_number = section_number;
            return this;
        }

        public Builder withSection_type(String section_type) {
            this.section_type = section_type;
            return this;
        }

        public Builder withInstructor_info_id(Integer instructor_info_id) {
            this.instructor_info_id = instructor_info_id;
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

        public Builder withCourse_info_id(Integer course_info_id) {
            this.course_info_id = course_info_id;
            return this;
        }

        public Builder withCalendar_info_id(Integer calendar_info_id) {
            this.calendar_info_id = calendar_info_id;
            return this;
        }

        public CreateSectionDto build() {
            return new CreateSectionDto(this);
        }
    }
}
