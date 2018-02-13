--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.0

-- Started on 2018-02-13 10:15:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2976 (class 1262 OID 17230)
-- Name: Course Scheduling System; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "Course Scheduling System" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE "Course Scheduling System" OWNER TO postgres;

\connect -reuse-previous=on "dbname='Course Scheduling System'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 1 (class 3079 OID 17231)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET search_path = public, pg_catalog;

--
-- TOC entry 231 (class 1255 OID 17513)
-- Name: insert_instructor_info_history(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION insert_instructor_info_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
INSERT INTO instructor_info_history(instructor_info_id, rank, course_load, phone_number, office, user_info_id, department)
VALUES(OLD.id, OLD.rank, OLD.course_load, OLD.phone_number, OLD.office, OLD.user_info_id, OLD.department);
RETURN NEW;
END;
$$;


ALTER FUNCTION public.insert_instructor_info_history() OWNER TO postgres;

--
-- TOC entry 228 (class 1255 OID 17511)
-- Name: insert_user_info_history(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION insert_user_info_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
INSERT INTO user_info_history(user_info_id, user_role, username, first_name, last_name, email, encrypted_password, salt, account_state)
VALUES(OLD.id, OLD.user_role, OLD.username, OLD.first_name, OLD.last_name, OLD.email, OLD.encrypted_password, OLD.salt, OLD.account_state);
RETURN NEW;
END;
$$;


ALTER FUNCTION public.insert_user_info_history() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 17240)
-- Name: calendar_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE calendar_info (
    id integer NOT NULL,
    year integer NOT NULL,
    semester character varying(255) NOT NULL,
    days character varying(255) NOT NULL,
    start_time integer NOT NULL,
    end_time integer NOT NULL,
    term integer NOT NULL
);


ALTER TABLE calendar_info OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 17246)
-- Name: calendar_info_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE calendar_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE calendar_info_id_seq OWNER TO postgres;

--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 198
-- Name: calendar_info_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE calendar_info_id_seq OWNED BY calendar_info.id;


--
-- TOC entry 199 (class 1259 OID 17248)
-- Name: course_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE course_info (
    id integer NOT NULL,
    course_name character varying(255) NOT NULL,
    required_frequency_per_term integer,
    required_frequency_per_semester integer,
    required_frequency_per_year integer,
    credit_amount double precision NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    department character varying(255) NOT NULL,
    course_number integer NOT NULL
);


ALTER TABLE course_info OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 17255)
-- Name: course_info_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE course_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE course_info_id_seq OWNER TO postgres;

--
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 200
-- Name: course_info_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE course_info_id_seq OWNED BY course_info.id;


--
-- TOC entry 201 (class 1259 OID 17257)
-- Name: instructor_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE instructor_info (
    id integer NOT NULL,
    rank integer NOT NULL,
    course_load integer NOT NULL,
    phone_number integer NOT NULL,
    office character varying(255),
    user_info_id integer,
    department character varying(255) NOT NULL
);


ALTER TABLE instructor_info OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17492)
-- Name: instructor_info_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE instructor_info_history (
    id integer NOT NULL,
    instructor_info_id integer NOT NULL,
    rank integer NOT NULL,
    course_load integer NOT NULL,
    phone_number integer NOT NULL,
    office character varying(255) NOT NULL,
    user_info_id integer,
    department character varying(255) NOT NULL
);


ALTER TABLE instructor_info_history OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17490)
-- Name: instructor_info_history_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE instructor_info_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE instructor_info_history_id_seq OWNER TO postgres;

--
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 219
-- Name: instructor_info_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE instructor_info_history_id_seq OWNED BY instructor_info_history.id;


--
-- TOC entry 202 (class 1259 OID 17263)
-- Name: instructor_info_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE instructor_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE instructor_info_id_seq OWNER TO postgres;

--
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 202
-- Name: instructor_info_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE instructor_info_id_seq OWNED BY instructor_info.id;


--
-- TOC entry 203 (class 1259 OID 17265)
-- Name: lab_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE lab_info (
    id integer NOT NULL,
    section_info_id integer,
    instructor_info_id integer,
    location character varying(255) NOT NULL,
    calendar_info_id integer
);


ALTER TABLE lab_info OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 17268)
-- Name: lab_info_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lab_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE lab_info_id_seq OWNER TO postgres;

--
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 204
-- Name: lab_info_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE lab_info_id_seq OWNED BY lab_info.id;


--
-- TOC entry 205 (class 1259 OID 17270)
-- Name: notifications; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE notifications (
    id integer NOT NULL,
    message character varying(2000),
    from_user_info_id integer,
    to_user_info_id integer
);


ALTER TABLE notifications OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 17276)
-- Name: notifications_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE notifications_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE notifications_id_seq OWNER TO postgres;

--
-- TOC entry 2985 (class 0 OID 0)
-- Dependencies: 206
-- Name: notifications_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE notifications_id_seq OWNED BY notifications.id;


--
-- TOC entry 207 (class 1259 OID 17278)
-- Name: schedule_links; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE schedule_links (
    id integer NOT NULL,
    instructor_info_id integer,
    section_info_id integer
);


ALTER TABLE schedule_links OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 17281)
-- Name: schedule_links_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE schedule_links_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE schedule_links_id_seq OWNER TO postgres;

--
-- TOC entry 2986 (class 0 OID 0)
-- Dependencies: 208
-- Name: schedule_links_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE schedule_links_id_seq OWNED BY schedule_links.id;


--
-- TOC entry 209 (class 1259 OID 17283)
-- Name: section_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE section_info (
    id integer NOT NULL,
    section_number integer NOT NULL,
    section_type character varying(255) NOT NULL,
    instructor_info_id integer,
    location character varying(255) NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    course_info_id integer,
    calendar_info_id integer
);


ALTER TABLE section_info OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 17290)
-- Name: section_info_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE section_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE section_info_id_seq OWNER TO postgres;

--
-- TOC entry 2987 (class 0 OID 0)
-- Dependencies: 210
-- Name: section_info_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE section_info_id_seq OWNED BY section_info.id;


--
-- TOC entry 211 (class 1259 OID 17292)
-- Name: section_population; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE section_population (
    id integer NOT NULL,
    expected_population integer NOT NULL,
    popoulation_cap integer NOT NULL,
    section_info_id integer
);


ALTER TABLE section_population OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 17295)
-- Name: section_population_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE section_population_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE section_population_id_seq OWNER TO postgres;

--
-- TOC entry 2988 (class 0 OID 0)
-- Dependencies: 212
-- Name: section_population_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE section_population_id_seq OWNED BY section_population.id;


--
-- TOC entry 213 (class 1259 OID 17297)
-- Name: user_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_info (
    id integer NOT NULL,
    user_role integer NOT NULL,
    username character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    encrypted_password character varying(255) NOT NULL,
    salt character varying(255) NOT NULL,
    account_state integer NOT NULL,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    login_time timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE user_info OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17473)
-- Name: user_info_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_info_history (
    id integer NOT NULL,
    user_info_id integer NOT NULL,
    user_role integer NOT NULL,
    username character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    encrypted_password character varying(255) NOT NULL,
    salt character varying(255) NOT NULL,
    account_state integer NOT NULL,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    login_time timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE user_info_history OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17471)
-- Name: user_info_history_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_info_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_info_history_id_seq OWNER TO postgres;

--
-- TOC entry 2989 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_info_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_info_history_id_seq OWNED BY user_info_history.id;


--
-- TOC entry 214 (class 1259 OID 17307)
-- Name: user_info_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_info_id_seq OWNER TO postgres;

--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 214
-- Name: user_info_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_info_id_seq OWNED BY user_info.id;


--
-- TOC entry 215 (class 1259 OID 17309)
-- Name: wishlist_links; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE wishlist_links (
    id integer NOT NULL,
    instructor_info_id integer,
    section_info_id integer
);


ALTER TABLE wishlist_links OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 17312)
-- Name: wishlist_links_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wishlist_links_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wishlist_links_id_seq OWNER TO postgres;

--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 216
-- Name: wishlist_links_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wishlist_links_id_seq OWNED BY wishlist_links.id;


--
-- TOC entry 2747 (class 2604 OID 17314)
-- Name: calendar_info id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY calendar_info ALTER COLUMN id SET DEFAULT nextval('calendar_info_id_seq'::regclass);


--
-- TOC entry 2749 (class 2604 OID 17315)
-- Name: course_info id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_info ALTER COLUMN id SET DEFAULT nextval('course_info_id_seq'::regclass);


--
-- TOC entry 2750 (class 2604 OID 17316)
-- Name: instructor_info id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY instructor_info ALTER COLUMN id SET DEFAULT nextval('instructor_info_id_seq'::regclass);


--
-- TOC entry 2767 (class 2604 OID 17495)
-- Name: instructor_info_history id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY instructor_info_history ALTER COLUMN id SET DEFAULT nextval('instructor_info_history_id_seq'::regclass);


--
-- TOC entry 2751 (class 2604 OID 17317)
-- Name: lab_info id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lab_info ALTER COLUMN id SET DEFAULT nextval('lab_info_id_seq'::regclass);


--
-- TOC entry 2752 (class 2604 OID 17318)
-- Name: notifications id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notifications ALTER COLUMN id SET DEFAULT nextval('notifications_id_seq'::regclass);


--
-- TOC entry 2753 (class 2604 OID 17319)
-- Name: schedule_links id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule_links ALTER COLUMN id SET DEFAULT nextval('schedule_links_id_seq'::regclass);


--
-- TOC entry 2755 (class 2604 OID 17320)
-- Name: section_info id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY section_info ALTER COLUMN id SET DEFAULT nextval('section_info_id_seq'::regclass);


--
-- TOC entry 2756 (class 2604 OID 17321)
-- Name: section_population id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY section_population ALTER COLUMN id SET DEFAULT nextval('section_population_id_seq'::regclass);


--
-- TOC entry 2761 (class 2604 OID 17322)
-- Name: user_info id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info ALTER COLUMN id SET DEFAULT nextval('user_info_id_seq'::regclass);


--
-- TOC entry 2763 (class 2604 OID 17476)
-- Name: user_info_history id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info_history ALTER COLUMN id SET DEFAULT nextval('user_info_history_id_seq'::regclass);


--
-- TOC entry 2762 (class 2604 OID 17323)
-- Name: wishlist_links id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wishlist_links ALTER COLUMN id SET DEFAULT nextval('wishlist_links_id_seq'::regclass);


--
-- TOC entry 2948 (class 0 OID 17240)
-- Dependencies: 197
-- Data for Name: calendar_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (1, 2018, 'Spring', 'MTRF', 1500, 1550, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (2, 2017, 'Fall', 'MTRF', 1000, 1050, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (3, 2017, 'Spring', 'MTRF', 1400, 1450, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (4, 2018, 'Spring', 'MTRF', 800, 850, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (5, 2018, 'Spring', 'MTRF', 1200, 1250, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (6, 2017, 'Spring', 'MR', 1100, 1250, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (7, 2017, 'Spring', 'TF', 1600, 1750, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (8, 2017, 'Fall', 'MTRF', 1300, 1350, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (9, 2018, 'Spring', 'MTRF', 1400, 1450, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (10, 2018, 'Spring', 'MTRF', 1300, 1350, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (11, 2018, 'Spring', 'MTRF', 1500, 1550, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (12, 2018, 'Spring', 'MR', 1400, 1550, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (13, 2018, 'Spring', 'MTRF', 900, 950, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (14, 2018, 'Spring', 'MR', 1100, 1250, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (15, 2018, 'Spring', 'MR', 1100, 1250, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (16, 2017, 'Fall', 'MTRF', 900, 950, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (17, 2017, 'Fall', 'W', 900, 950, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (18, 2017, 'Fall', 'MTRF', 900, 950, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (19, 2017, 'Fall', 'W', 1000, 1050, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (20, 2017, 'Fall', 'MTRF', 900, 950, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (21, 2017, 'Fall', 'W', 1100, 1150, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (22, 2017, 'Fall', 'MTRF', 900, 900, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (23, 2017, 'Fall', 'W', 1200, 1250, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (24, 2017, 'Fall', 'MTRF', 900, 950, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (25, 2017, 'Fall', 'W', 1300, 1350, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (26, 2017, 'Spring', 'MTRF', 900, 950, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (27, 2017, 'Spring', 'W', 900, 950, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (28, 2017, 'Spring', 'MTRF', 900, 950, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (29, 2017, 'Spring', 'W', 1000, 1050, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (30, 2017, 'Spring', 'MTRF', 900, 950, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (31, 2017, 'Spring', 'W', 1100, 1150, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (32, 2017, 'Spring', 'MTRF', 900, 950, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (33, 2017, 'Spring', 'W', 800, 850, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (34, 2017, 'Spring', 'MTRF', 900, 950, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (35, 2017, 'Spring', 'W', 1200, 1250, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (36, 2017, 'Fall', 'MTRF', 1300, 1350, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (37, 2017, 'Fall', 'MTRF', 1200, 1250, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (38, 2017, 'Fall', 'W', 800, 850, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (39, 2017, 'Fall', 'W', 1200, 1250, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (40, 2017, 'Fall', 'W', 1300, 1350, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (41, 2017, 'Fall', 'W', 1400, 1450, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (42, 2017, 'Fall', 'W', 1600, 1650, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (43, 2017, 'Fall', 'W', 900, 950, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (44, 2017, 'Fall', 'T', 1500, 1550, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (45, 2017, 'Fall', 'W', 1500, 1550, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (46, 2017, 'Fall', 'W', 1500, 1550, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (47, 2017, 'Fall', 'W', 1700, 1750, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (48, 2017, 'Fall', 'W', 1000, 1050, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (49, 2017, 'Fall', 'W', 1600, 1650, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (50, 2017, 'Fall', 'W', 1100, 1150, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (51, 2018, 'Spring', 'MTRF', 1200, 1250, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (52, 2017, 'Spring', 'W', 1300, 1350, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (53, 2017, 'Spring', 'W', 1400, 1450, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (54, 2017, 'Spring', 'W', 1500, 1550, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (55, 2017, 'Spring', 'W', 1600, 1650, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (56, 2017, 'Fall', 'MTRF', 1600, 1650, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (57, 2017, 'Fall', 'R', 900, 950, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (58, 2017, 'Fall', 'MTRF', 1600, 1650, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (59, 2017, 'Fall', 'R', 1000, 1050, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (60, 2017, 'Fall', 'MTRF', 1600, 1650, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (61, 2017, 'Fall', 'R', 1100, 1150, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (62, 2017, 'Fall', 'MTRF', 1600, 1650, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (63, 2017, 'Fall', 'R', 1300, 1350, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (64, 2017, 'Fall', 'MTRF', 1400, 1450, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (65, 2017, 'Spring', 'MTRF', 1000, 1050, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (66, 2017, 'Spring', 'MTRF', 1500, 1550, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (67, 2017, 'Fall', 'MR', 800, 950, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (68, 2017, 'Fall', 'W', 1400, 1450, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (69, 2017, 'Fall', 'MR', 800, 950, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (70, 2017, 'Fall', 'W', 1500, 1550, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (71, 2017, 'Fall', 'MR', 800, 950, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (72, 2017, 'Fall', 'W', 1600, 1650, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (73, 2018, 'Spring', 'MR', 1100, 1250, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (74, 2017, 'Spring', 'W', 1400, 1450, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (75, 2018, 'Spring', 'MR', 1100, 1250, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (76, 2017, 'Spring', 'W', 1500, 1550, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (77, 2018, 'Spring', 'MR', 1100, 1250, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (78, 2017, 'Spring', 'W', 1600, 1650, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (79, 2017, 'Fall', 'MTRF', 1400, 1450, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (80, 2017, 'Fall', 'W', 1400, 1450, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (81, 2017, 'Fall', 'MTRF', 1000, 1050, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (82, 2017, 'Fall', 'MTRF', 1600, 1650, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (83, 2017, 'Fall', 'MR', 1200, 1350, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (84, 2017, 'Fall', 'TF', 1500, 1650, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (85, 2018, 'Spring', 'MR', 1400, 1550, 4);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (86, 2017, 'Fall', 'MR', 1200, 1350, 2);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (87, 2017, 'Fall', 'W', 800, 850, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (88, 2017, 'Fall', 'MTRF', 800, 850, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (89, 2017, 'Fall', 'W', 900, 950, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (90, 2017, 'Fall', 'MTRF', 900, 950, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (91, 2017, 'Fall', 'MTRF', 1000, 1015, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (92, 2017, 'Fall', 'MTRF', 900, 950, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (93, 2017, 'Fall', 'MTRF', 800, 850, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (94, 2017, 'Fall', 'MTRF', 800, 850, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (95, 2017, 'Fall', 'MTRF', 800, 850, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (96, 2017, 'Fall', 'MTRF', 800, 850, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (97, 2017, 'Fall', 'MTRF', 800, 850, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (98, 2017, 'Fall', 'W', 900, 950, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (99, 2017, 'Fall', 'W', 1000, 1050, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (100, 2017, 'Fall', 'W', 1200, 1250, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (101, 2017, 'Fall', 'W', 1100, 1150, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (102, 2017, 'Fall', 'W', 800, 850, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (103, 2017, 'Fall', 'MTRF', 1000, 1050, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (104, 2017, 'Fall', 'MR', 1300, 1350, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (105, 2017, 'Fall', 'MTRF', 900, 950, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (106, 2017, 'Fall', 'TF', 1400, 1550, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (107, 2017, 'Fall', '', 0, 0, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (108, 2017, 'Fall', 'MR', 1200, 1350, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (109, 2017, 'Fall', 'MTRF', 1200, 1250, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (110, 2017, 'Fall', 'MTRF', 1300, 1350, 1);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (111, 2018, 'SPRING', 'MTRF', 800, 850, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (112, 2018, 'SPRING', 'T', 800, 850, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (113, 2018, 'SPRING', 'W', 1100, 1150, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (114, 2018, 'SPRING', 'W', 1500, 1550, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (115, 2018, 'SPRING', 'W', 1600, 1650, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (116, 2018, 'SPRING', 'T', 1300, 1350, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (117, 2018, 'SPRING', 'T', 1100, 1150, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (118, 2018, 'SPRING', 'MTRF', 1000, 1050, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (119, 2018, 'SPRING', 'W', 1300, 1350, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (120, 2018, 'SPRING', 'W', 1200, 1250, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (121, 2018, 'SPRING', 'W', 1000, 1050, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (122, 2018, 'SPRING', 'W', 1400, 1450, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (123, 2018, 'SPRING', 'W', 900, 950, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (124, 2018, 'SPRING', 'MTRF', 1500, 1550, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (125, 2018, 'SPRING', 'M', 1100, 1150, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (126, 2018, 'SPRING', 'MTRF', 1500, 1550, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (127, 2018, 'SPRING', 'M', 1100, 1150, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (128, 2018, 'SPRING', 'MTRF', 1500, 1550, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (129, 2018, 'SPRING', 'M', 1200, 1250, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (130, 2018, 'SPRING', 'MTRF', 1500, 1550, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (131, 2018, 'SPRING', 'M', 1000, 1050, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (132, 2018, 'SPRING', 'MTRF', 1000, 1050, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (133, 2018, 'SPRING', 'MTRF', 1600, 1650, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (134, 2018, 'SPRING', 'W', 1100, 1150, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (135, 2018, 'SPRING', 'W', 1400, 1450, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (136, 2018, 'SPRING', 'W', 1500, 1550, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (137, 2018, 'SPRING', 'W', 1600, 1650, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (138, 2018, 'SPRING', 'W', 1200, 1250, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (139, 2018, 'SPRING', 'T', 1500, 1550, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (140, 2018, 'SPRING', 'T', 1300, 1350, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (141, 2018, 'SPRING', 'TF', 1200, 1350, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (142, 2018, 'SPRING', 'TF', 900, 1050, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (143, 2018, 'SPRING', 'MTRF', 1400, 1450, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (144, 2018, 'SPRING', 'MR', 1100, 1250, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (145, 2018, 'SPRING', 'MTRF', 1500, 1550, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (146, 2018, 'SPRING', 'MTRF', 1600, 1650, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (147, 2018, 'SPRING', 'MTRF', 1400, 1450, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (148, 2018, 'SPRING', 'W', 1300, 1450, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (149, 2018, 'SPRING', 'MTRF', 1000, 1050, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (150, 2018, 'SPRING', 'W', 1000, 1050, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (151, 2018, 'SPRING', 'W', 1100, 1150, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (152, 2018, 'SPRING', 'MR', 1200, 1350, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (153, 2018, 'SPRING', 'MR', 1400, 1550, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (154, 2018, 'SPRING', 'MTRF', 1500, 1550, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (155, 2018, 'SPRING', 'MTRF', 1000, 1050, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (156, 2018, 'SPRING', 'MW', 1600, 1650, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (157, 2018, 'SPRING', 'MW', 1600, 1650, 3);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (190, 2017, 'FALL', 'MW', 1600, 1720, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (191, 2017, 'FALL', 'R', 1800, 2050, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (192, 2017, 'FALL', 'R', 1800, 2050, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (193, 2017, 'FALL', 'W', 1800, 2050, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (194, 2017, 'FALL', 'T', 1800, 2050, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (195, 2017, 'FALL', 'T', 1800, 2050, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (196, 2018, 'SPRING', 'R', 1800, 2050, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (197, 2018, 'SPRING', 'R', 1800, 2050, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (198, 2017, 'FALL', 'M', 1800, 2050, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (199, 2018, 'SPRING', 'R', 1800, 2050, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (206, 2017, 'FALL', 'R', 1730, 2020, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (207, 2017, 'FALL', 'R', 1730, 2020, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (208, 2017, 'FALL', 'R', 1800, 2050, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (209, 2017, 'FALL', 'M', 1800, 2050, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (210, 2017, 'SPRING', 'R', 1800, 2050, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (211, 2017, 'FALL', 'TR', 1600, 1720, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (212, 2017, 'FALL', 'TR', 1600, 1720, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (213, 2017, 'FALL', 'TR', 1600, 1720, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (214, 2017, 'FALL', 'M', 1800, 2050, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (215, 2017, 'FALL', 'W', 1000, 1250, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (216, 2017, 'FALL', 'W', 1000, 1250, 5);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (229, 2018, 'SPRING', 'MW', 1300, 1420, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (230, 2018, 'SPRING', 'W', 1800, 2050, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (231, 2018, 'SPRING', 'W', 1800, 2050, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (232, 2018, 'SPRING', 'TR', 1600, 1720, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (233, 2018, 'SPRING', 'MW', 1200, 1320, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (234, 2018, 'SPRING', 'W', 1800, 2050, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (235, 2018, 'SPRING', 'MW', 1600, 1720, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (236, 2018, 'SPRING', 'MW', 1600, 1750, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (237, 2018, 'SPRING', 'MR', 1000, 1150, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (238, 2018, 'SPRING', '', 0, 0, 6);
INSERT INTO calendar_info (id, year, semester, days, start_time, end_time, term) VALUES (239, 2018, 'SPRING', 'R', 1800, 2050, 6);


--
-- TOC entry 2950 (class 0 OID 17248)
-- Dependencies: 199
-- Data for Name: course_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (1, 'DISCRETE MATH', 7, 7, 7, 3, false, 'CS', 2022);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (2, 'APPLICATION BUILDING WITH OBJECT ORIENTED CONCEPTS', 4, 4, 8, 3, false, 'CS', 2119);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (16, 'HUMAN COMPUTER INTERACTION', 1, 1, 2, 3, false, 'CS', 3041);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (17, 'SOCIAL IMPLICATIONS OF INFORMATION PROCESSING', 4, 4, 8, 3, false, 'CS', 3043);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (18, 'SOFTWARE ENGINEERING', 3, 3, 6, 3, false, 'CS', 3733);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (19, 'MACHINE LEARNING', 1, 2, 4, 3, false, 'CS', 453);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (20, 'ANALYSIS OF ALGORITHMS', 1, 1, 2, 3, false, 'CS', 4120);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (21, 'SOFTWARE SECURITY ENGINEERING', 1, 1, 2, 3, false, 'CS', 4401);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (22, 'DATABASE SYSTEMS II', 1, 1, 2, 3, false, 'CS', 4432);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (23, 'DISTRIBUTED COMPUTER SYSTEMS', 1, 1, 1, 3, false, 'CS', 4513);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (24, 'INTRODUCTION TO MACHINE ORGANIZATION AND ASSEMBLY LANGUAGE', 5, 5, 10, 3, false, 'CS', 2011);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (25, 'OBJECT-ORIENTED DESIGN CONCEPTS', 2, 2, 4, 3, false, 'CS', 2102);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (26, 'ACCELERATED OBJECT-ORIENTED DESIGN CONCEPTS', 4, 4, 8, 3, false, 'CS', 2102);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (27, 'ALGORITHMS', 1, 1, 2, 3, false, 'CS', 2223);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (28, 'SYSTEM PROGRAMMING FOR NON-MAJORS', 3, 3, 6, 3, false, 'CS', 2301);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (29, 'NUMERICAL METHODS FOR CALCULUS AND DIFFERENTIAL EQUATIONS', 1, 1, 1, 3, false, 'CS', 4033);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (30, 'ARTIFICIAL INTELLIGENCE FOR INTERACTIVE MEDIA AND GAMES', 1, 1, 1, 3, false, 'CS', 4100);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (31, 'THEORY OF COMPUTATION', 1, 1, 1, 3, false, 'CS', 4123);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (32, 'DATA MINING AND KNOWLEDGE DISCOVERY IN DATABASES', 1, 1, 1, 3, false, 'CS', 4445);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (33, 'BIOLOGICAL AND BIOMEDICAL DATABASE MINING', 1, 1, 1, 3, false, 'CS', 4803);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (34, 'MOBILE & UBIQUITOUS COMPUTING', 1, 1, 1, 3, false, 'CS', 4518);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (35, 'INTRODUCTION TO PROGRAMMING FOR NON-MAJORS', 1, 1, 2, 3, false, 'CS', 1004);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (36, 'INTRODUCTION TO PROGRAM DESIGN', 3, 3, 6, 3, false, 'CS', 1101);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (37, 'ACCELERATED INTRODUCTION TO PROGRAM DESIGN', 1, 1, 2, 3, false, 'CS', 1102);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (38, 'SYSTEMS PROGRAMMING CONCEPTS', 5, 10, 20, 3, false, 'CS', 2303);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (39, 'FOUNDATIONS OF COMPUTER SCIENCE', 1, 1, 2, 3, false, 'CS', 3133);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (40, 'DATABASE SYSTEMS', 1, 1, 2, 3, false, 'CS', 3431);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (41, 'COMPUTER NETWORKS', 1, 2, 4, 3, false, 'CS', 3516);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (42, 'OBJECT-ORIENTED ANALYSIS AND DESIGN', 1, 1, 2, 3, false, 'CS', 4233);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (43, 'WEBWARE: COMPUTATIONAL TECHNOLOGY FOR NETWORK INFORMATION SYSTEMS', 1, 1, 2, 3, false, 'CS', 4241);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (44, 'INTRODUCTION TO ARTIFICIAL INTELLIGENCE', 2, 2, 4, 3, false, 'CS', 4341);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (45, 'NUMERICAL METHODS FOR LINEAR AND NONLINEAR SYSTEMS', 2, 2, 4, 3, false, 'CS', 4032);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (46, 'PROGRAMMING LANGUAGES', 1, 1, 2, 3, false, 'CS', 4536);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (47, 'COMPUTER GRAPHICS', 1, 1, 2, 3, false, 'CS', 4731);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (48, 'BIOVISUALIZATION', 1, 1, 2, 3, false, 'CS', 4802);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (49, 'Operating Systems', 2, 2, 2, 3, false, 'CS', 3013);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (72, 'INTRODUCTION TO PROGRAMMING CONCEPTS, DATA STRUCTURES AND ALGORITHMS', 1, 1, 1, 3, false, 'CS', 5007);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (73, 'OPERATING SYSTEMS', 2, 2, 2, 3, false, 'CS', 502);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (74, 'INTRODUCTION TO ALGORITHMS: DESIGN AND ANALYSIS', 1, 1, 1, 3, false, 'CS', 5084);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (75, 'DESIGN OF SOFTWARE SYSTEMS', 2, 2, 4, 3, false, 'CS', 509);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (78, 'COMPUTER NETWORKS 1', 1, 1, 1, 3, false, 'CS', 513);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (85, 'NUMERICAL METHODS', 1, 1, 1, 3, false, 'MA', 522);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (86, 'NUMERICAL METHODS 1', 1, 1, 1, 3, false, 'MA', 523);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (87, 'MOBILE AND UBIQUITOUS COMPUTING', 1, 1, 1, 3, false, 'CS', 528);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (88, 'ARTIFICIAL INTELLIGENCE', 1, 1, 2, 3, false, 'CS', 534);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (89, 'KNOWLEDGE, DISCOVERY AND DATA MINING', 1, 1, 1, 3, false, 'CS', 548);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (90, 'BIOLOGICAL AND BIOMEDICAL DATABASE MINING 1', 1, 1, 1, 3, false, 'CS', 583);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (91, 'COMPUTER NETWORK SECURITY', 1, 1, 1, 3, false, 'CS', 558);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (92, 'CRYPTOGRAPHY AND DATA SECURITY', 1, 1, 1, 3, false, 'ECE', 578);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (93, 'ALGORITHMS: DESIGN AND ANALYSIS', 2, 2, 2, 3, false, 'CS', 584);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (101, 'SPECIAL TOPICS', 5, 5, 6, 3, false, 'CS', 525);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (102, 'DATABASE MANAGEMENT SYSTEMS', 1, 1, 2, 3, false, 'CS', 542);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (103, 'USER MODELING', 1, 1, 1, 3, false, 'CS', 565);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (104, 'DATA VISUALIZATION', 1, 1, 2, 3, false, 'CS', 573);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (105, 'BIG DATA MANAGEMENT', 2, 2, 3, 3, false, 'CS', 585);
INSERT INTO course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) VALUES (106, 'BIG DATA ANALYTICS', 1, 1, 2, 3, false, 'CS', 586);


--
-- TOC entry 2952 (class 0 OID 17257)
-- Dependencies: 201
-- Data for Name: instructor_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (2, 1, 0, 5, 'NA', 192, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (3, 1, 0, 5, 'NA', 193, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (4, 1, 0, 5, 'NA', 194, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (5, 1, 0, 5, 'NA', 195, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (6, 1, 0, 5, 'NA', 196, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (7, 1, 0, 5, 'NA', 197, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (8, 1, 0, 5, 'NA', 198, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (9, 1, 0, 5, 'NA', 199, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (10, 1, 0, 5, 'NA', 200, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (11, 1, 0, 5, 'NA', 201, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (12, 1, 0, 5, 'NA', 202, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (13, 1, 0, 5, 'NA', 203, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (14, 1, 0, 5, 'NA', 204, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (15, 1, 0, 5, 'NA', 205, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (16, 1, 0, 5, 'NA', 206, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (17, 1, 0, 5, 'NA', 207, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (18, 1, 0, 5, 'NA', 208, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (19, 1, 0, 5, 'NA', 209, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (20, 1, 0, 5, 'NA', 210, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (21, 1, 0, 5, 'NA', 211, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (22, 1, 0, 5, 'NA', 212, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (23, 1, 0, 5, 'NA', 213, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (24, 1, 0, 5, 'NA', 214, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (25, 1, 0, 5, 'NA', 215, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (26, 1, 0, 5, 'NA', 216, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (27, 1, 0, 5, 'NA', 217, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (28, 1, 0, 5, 'NA', 218, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (29, 1, 0, 5, 'NA', 219, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (30, 1, 0, 5, 'NA', 220, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (31, 1, 0, 5, 'NA', 221, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (32, 1, 0, 5, 'NA', 222, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (33, 1, 0, 5, 'NA', 223, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (34, 1, 0, 5, 'NA', 224, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (35, 1, 0, 5, 'NA', 225, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (36, 1, 0, 5, 'NA', 226, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (37, 1, 0, 5, 'NA', 227, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (38, 1, 0, 5, 'NA', 228, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (39, 1, 0, 5, 'NA', 229, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (40, 1, 0, 5, 'NA', 230, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (41, 1, 0, 5, 'NA', 231, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (42, 1, 0, 5, 'NA', 232, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (43, 1, 0, 5, 'NA', 233, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (44, 1, 0, 5, 'NA', 234, 'CS');
INSERT INTO instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) VALUES (1, 1, 0, 5, 'NA', 191, 'CS');


--
-- TOC entry 2971 (class 0 OID 17492)
-- Dependencies: 220
-- Data for Name: instructor_info_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO instructor_info_history (id, instructor_info_id, rank, course_load, phone_number, office, user_info_id, department) VALUES (5, 1, 1, 0, 5, 'NA', NULL, 'CS');


--
-- TOC entry 2954 (class 0 OID 17265)
-- Dependencies: 203
-- Data for Name: lab_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (1, 26, NULL, 'FL A021', 17);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (2, 27, NULL, 'FL A021', 19);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (3, 28, NULL, 'SL 123', 21);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (13, 37, NULL, 'SL 123', 23);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (14, 38, NULL, 'GH 102', 25);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (15, 39, NULL, 'SL 123', 27);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (16, 40, NULL, 'SL 123', 29);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (17, 41, NULL, 'SL 123', 31);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (18, 42, NULL, 'SL 123', 33);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (19, 43, NULL, 'SL 123', 35);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (20, 45, NULL, 'FL A021', 38);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (21, 45, NULL, 'FL A021', 39);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (22, 45, NULL, 'FL A021', 40);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (23, 45, NULL, 'FL A021', 41);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (24, 45, NULL, 'SL 123', 42);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (25, 45, NULL, 'SL 123', 43);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (26, 45, NULL, 'FL A021', 44);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (27, 45, NULL, 'SL 123', 45);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (28, 45, NULL, 'FL A021', 46);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (29, 45, NULL, 'FL A021', 47);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (30, 45, NULL, 'SL 123', 48);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (31, 45, NULL, 'FL A021', 49);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (32, 45, NULL, 'FL A021', 50);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (33, 46, NULL, 'SL 123', 52);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (34, 46, NULL, 'SL 123', 53);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (35, 46, NULL, 'SL 123', 54);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (36, 46, NULL, 'SL 123', 55);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (37, 47, NULL, 'FL A021', 57);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (38, 48, NULL, 'FL A021', 59);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (39, 49, NULL, 'FL A021', 61);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (40, 50, NULL, 'FL A021', 63);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (41, 54, NULL, 'HL 230', 68);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (42, 55, NULL, 'HL 230', 70);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (43, 56, NULL, 'HL 230', 72);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (44, 57, NULL, 'GH 012', 74);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (45, 58, NULL, 'GH 012', 76);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (46, 59, NULL, 'GH 012', 78);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (47, 60, NULL, 'OH 223', 80);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (48, 73, NULL, 'KH 203', 98);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (49, 74, NULL, 'KH 203', 99);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (50, 75, NULL, 'KH 203', 100);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (51, 76, NULL, 'KH 203', 101);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (52, 77, NULL, 'KH 203', 102);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (53, 86, NULL, 'SL 123', 112);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (54, 86, NULL, 'SL 123', 113);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (55, 86, NULL, 'SL 123', 114);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (56, 86, NULL, 'SL 123', 115);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (57, 86, NULL, 'SL 123', 116);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (58, 86, NULL, 'SL 123', 117);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (59, 87, NULL, 'SL 123', 119);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (60, 87, NULL, 'SL 123', 120);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (61, 87, NULL, 'SL 123', 121);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (62, 87, NULL, 'SL 123', 122);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (63, 87, NULL, 'SL 123', 123);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (64, 93, NULL, 'GH 012', 135);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (65, 93, NULL, 'GH 012', 136);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (66, 93, NULL, 'GH 012', 137);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (67, 94, NULL, 'GH 012', 138);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (68, 94, NULL, 'GH 012', 139);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (69, 94, NULL, 'GH 012', 140);
INSERT INTO lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) VALUES (70, 94, NULL, 'GH 012', 141);


--
-- TOC entry 2956 (class 0 OID 17270)
-- Dependencies: 205
-- Data for Name: notifications; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2958 (class 0 OID 17278)
-- Dependencies: 207
-- Data for Name: schedule_links; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2960 (class 0 OID 17283)
-- Dependencies: 209
-- Data for Name: section_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (11, 1, 'Lecture', NULL, 'AK 116', false, 1, 1);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (12, 2, 'Lecture', NULL, 'AK 116', false, 1, 2);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (13, 1, 'Lecture', NULL, 'FL PH-LWR', false, 2, 3);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (14, 2, 'Lecture', NULL, 'GH 012', false, 2, 4);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (15, 2, 'Lecture', NULL, 'AK 223', false, 16, 5);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (16, 1, 'Lecture', NULL, 'FL 311', false, 17, 6);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (17, 2, 'Lecture', NULL, 'SH 106', false, 17, 7);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (18, 1, 'Lecture', NULL, 'WB 229', false, 18, 8);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (19, 2, 'Lecture', NULL, 'FL PH-LWR', false, 18, 9);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (20, 1, 'Lecture', NULL, 'SH 308', false, 19, 10);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (21, 1, 'Lecture', NULL, 'HL 116', false, 20, 11);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (22, 1, 'Lecture', NULL, 'HL 116', false, 20, 12);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (23, 1, 'Lecture', NULL, 'FL 320', false, 21, 13);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (24, 1, 'Lecture', NULL, 'FL PH-LWR', false, 22, 14);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (25, 1, 'Lecture', NULL, 'FL PH-LWR', false, 23, 15);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (26, 1, 'LECTURE', NULL, 'SL 115', false, 24, 16);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (27, 2, 'LECTURE', NULL, 'SL 115', false, 24, 18);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (28, 3, 'LECTURE', NULL, 'SL 115', false, 24, 20);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (37, 4, 'LECTURE', NULL, 'SL 115', false, 24, 22);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (38, 5, 'LECTURE', NULL, 'SL 115', false, 24, 24);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (39, 1, 'LECTURE', NULL, 'FL PH-UPR', false, 24, 26);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (40, 2, 'LECTURE', NULL, 'FL PH-UPR', false, 24, 28);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (41, 3, 'LECTURE', NULL, 'FL PH-UPR', false, 24, 30);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (42, 4, 'LECTURE', NULL, 'FL PH-UPR', false, 24, 32);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (43, 5, 'LECTURE', NULL, 'FL PH-UPR', false, 24, 34);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (44, 1, 'LECTURE', NULL, 'AK 116', false, 25, 36);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (45, 2, 'LECTURE', NULL, 'AK 116', false, 25, 37);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (46, 1, 'LECTURE', NULL, 'OH 107', false, 25, 51);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (47, 1, 'LECTURE', NULL, 'FL PH-LWR', false, 26, 56);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (48, 2, 'LECTURE', NULL, 'FL PH-LWR', false, 26, 58);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (49, 3, 'LECTURE', NULL, 'FL PH-LWR', false, 26, 60);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (50, 4, 'LECTURE', NULL, 'FL PH-LWR', false, 26, 62);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (51, 1, 'LECTURE', NULL, 'FL PH-UPR', false, 27, 64);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (52, 1, 'LECTURE', NULL, 'FL PH-UPR', false, 27, 65);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (53, 2, 'LECTURE', NULL, 'AK 233', false, 27, 66);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (54, 1, 'LECTURE', NULL, 'FL PH-LWR', false, 28, 67);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (55, 2, 'LECTURE', NULL, 'FL PH-LWR', false, 28, 69);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (56, 3, 'LECTURE', NULL, 'FL PH-LWR', false, 28, 71);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (57, 1, 'LECTURE', NULL, 'SL 104', false, 28, 73);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (58, 2, 'LECTURE', NULL, 'SL 104', false, 28, 75);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (59, 1, 'LECTURE', NULL, 'SL 104', false, 28, 77);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (60, 1, 'LECTURE', NULL, 'OH 223', false, 29, 79);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (61, 1, 'LECTURE', NULL, 'FL 320', false, 30, 81);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (62, 1, 'LECTURE', NULL, 'SL 105', false, 31, 82);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (63, 1, 'LECTURE', NULL, 'FL 320', false, 32, 83);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (64, 1, 'LECTURE', NULL, 'WB 229', false, 34, 84);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (65, 1, 'LECTURE', NULL, 'KH 116', false, 34, 85);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (66, 1, 'LECTURE', NULL, 'FL 320', false, 33, 86);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (67, 1, 'lab', NULL, 'GH012', false, 35, 87);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (68, 1, 'Lecture', NULL, 'AK116', false, 35, 88);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (69, 2, 'lab', NULL, 'GH012', false, 35, 89);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (70, 1, 'Lecture', NULL, 'FL PH-UPR', false, 36, 90);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (71, 2, 'Lecture', NULL, 'FL PH-UPR', false, 36, 91);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (72, 1, 'Lecture', NULL, 'FL PH-LWR', false, 37, 92);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (73, 1, 'Lecture', NULL, 'FL PH-UPR', false, 38, 93);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (74, 2, 'Lecture', NULL, 'FL PH-UPR', false, 38, 94);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (75, 3, 'Lecture', NULL, 'FL PH-UPR', false, 38, 95);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (76, 4, 'Lecture', NULL, 'FL PH-UPR', false, 38, 96);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (77, 5, 'Lecture', NULL, 'FL PH-UPR', false, 38, 97);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (78, 1, 'Lecture', NULL, 'FL PH-LWR', false, 39, 103);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (79, 1, 'Lecture', NULL, 'FL 320', false, 40, 104);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (80, 1, 'Lecture', NULL, 'FL 320', false, 41, 105);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (81, 1, 'Lecture', NULL, 'FL 320', false, 41, 106);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (82, 1, 'Lecture', NULL, 'Web', false, 42, 107);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (83, 1, 'Lecture', NULL, 'FL PH-LWR', false, 43, 108);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (84, 1, 'Lecture', NULL, 'KH 116', false, 44, 109);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (85, 2, 'Lecture', NULL, 'SL 104', false, 44, 10);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (86, 1, 'Lecture', NULL, 'SL 115', false, 36, 118);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (87, 1, 'Lecture', NULL, 'SL 115', false, 35, 111);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (88, 1, 'Lecture', NULL, 'AK 116', false, 1, 124);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (89, 1, 'Conference', NULL, 'AK 308', false, 1, 126);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (90, 2, 'Conference', NULL, 'SH 306', false, 1, 128);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (91, 3, 'Conference', NULL, 'SH 304', false, 1, 130);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (92, 4, 'Conference', NULL, 'SH 309', false, 1, 132);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (93, 1, 'Lecture', NULL, 'FL PH-LWR', false, 38, 133);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (94, 2, 'Lecture', NULL, 'FL PH-UPR', false, 38, 134);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (95, 1, 'Lecture', NULL, 'AK 219', false, 49, 142);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (96, 2, 'Lecture', NULL, 'KH 116', false, 49, 143);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (97, 1, 'Lecture', NULL, 'SH 106', false, 17, NULL);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (98, 2, 'Lecture', NULL, 'FL 311', false, 17, NULL);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (99, 1, 'Lecture', NULL, 'FL PH-UPR', false, 39, 146);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (100, 1, 'Lecture', NULL, 'HL 116', false, 40, 147);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (101, 1, 'Lecture', NULL, 'WB 229', false, 18, 148);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (102, 1, 'Lecture', NULL, 'WB 229', false, 18, 149);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (103, 1, 'Lecture', NULL, 'SH 308', false, 45, 150);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (104, 1, 'Conference', NULL, 'SH 308', false, 45, 151);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (105, 2, 'Conference', NULL, 'SH 308', false, 45, 152);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (106, 1, 'Lecture', NULL, 'KH 116', false, 44, 153);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (107, 1, 'Lecture', NULL, 'KH 116', false, 34, 154);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (108, 1, 'Lecture', NULL, 'SH 308', false, 46, 155);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (109, 1, 'Lecture', NULL, 'FL PH-LWR', false, 47, 156);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (110, 1, 'Lecture', NULL, 'GH 227', false, 48, 157);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (112, 191, 'Lecture', NULL, 'FL320', false, 72, 190);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (113, 191, 'Lecture', NULL, 'FL320', false, 73, 191);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (114, 192, 'Lecture', NULL, 'FL320', false, 73, 192);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (115, 191, 'Lecture', NULL, 'FL311', false, 74, 193);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (116, 191, 'Lecture', NULL, 'FL320', false, 75, 194);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (117, 192, 'Lecture', NULL, 'FL320', false, 75, 195);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (118, 191, 'Lecture', NULL, 'SH308', false, 75, 196);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (119, 192, 'Lecture', NULL, 'SH308', false, 75, 197);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (120, 191, 'Lecture', NULL, 'FL PH-LWR', false, 78, 198);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (121, 191, 'Lecture', NULL, 'SH202', false, 85, 199);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (122, 191, 'Lecture', NULL, 'SH202', false, 85, 206);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (123, 191, 'Lecture', NULL, 'OH126', false, 87, 207);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (124, 191, 'Lecture', NULL, 'HL116', false, 88, 208);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (125, 191, 'Lecture', NULL, 'HL218', false, 88, 209);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (126, 191, 'Lecture', NULL, 'FL320', false, 89, 210);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (127, 191, 'Lecture', NULL, 'FL320', false, 90, 211);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (128, 191, 'Lecture', NULL, 'HL114', false, 91, 212);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (129, 191, 'Lecture', NULL, 'AK233', false, 92, 214);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (130, 191, 'Lecture', NULL, 'SH309', false, 93, 215);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (131, 192, 'Lecture', NULL, 'SH309', false, 93, 216);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (132, 191, 'Lecture', NULL, 'SH306', false, 101, 229);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (133, 191, 'Lecture', NULL, 'FL311', false, 101, 230);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (134, 191, 'Lecture', NULL, 'AK232', false, 101, 231);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (135, 191, 'Lecture', NULL, 'SL402', false, 19, 232);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (136, 191, 'Lecture', NULL, 'SL402', false, 102, 233);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (137, 191, 'Lecture', NULL, 'FL320', false, 47, 234);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (138, 191, 'Lecture', NULL, 'OH218', false, 103, 235);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (139, 191, 'Lecture', NULL, 'GH227', false, 104, 236);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (140, 191, 'Lecture', NULL, 'SL305', false, 105, 237);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (141, 1960, 'Lecture', NULL, 'WEB', false, 105, 238);
INSERT INTO section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) VALUES (142, 191, 'Lecture', NULL, 'AK233', false, 106, 239);


--
-- TOC entry 2962 (class 0 OID 17292)
-- Dependencies: 211
-- Data for Name: section_population; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2964 (class 0 OID 17297)
-- Dependencies: 213
-- Data for Name: user_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (191, 1, 'emmanuel', 'Agu', 'Emmanuel', 'emmanuel@wpi.edu', false, 'password', '1026', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (192, 1, 'gpollice', 'Pollice', 'Gary', 'gpollice@wpi.edu', false, 'password', '1027', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (193, 1, 'ghamel', 'Hamel', 'Glynis', 'ghamel@wpi.edu', false, 'password', '1028', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (194, 1, 'heineman', 'Heineman', 'George', 'heineman@wpi.edu', false, 'password', '1029', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (195, 1, 'gsarkozy', 'Sarkozy', 'Gabor', 'gsarkozy', false, 'password', '1030', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (196, 1, 'hcloi', 'Loi', 'Hao', 'hcloi@wpi.edu', false, 'password', '1031', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (197, 1, 'lauer', 'Lauer', 'Hugh', 'lauer@wpi.edu', false, 'password', '1032', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (198, 1, 'hservat', 'Servatius', 'Herman', 'hservat', false, 'password', '1033', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (199, 1, 'amahadev', 'Aparna', 'Mahadev', 'amahadev@wpi.edu', false, 'password', '1', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (200, 1, 'akabir', 'Kabir', 'Ahmedul', 'akabir@wpi.edu', false, 'password', '2', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (201, 1, 'benelson', 'Nelson', 'Blake', 'benelson@wpi.edu', false, 'password', '3', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (202, 1, 'cew', 'Wills', 'Craig', 'cew@wpi.edu', false, 'password', '4', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (204, 1, 'ruiz', 'Carolina', 'Ruiz', 'ruiz@wpi.edu', false, 'password', '1010101', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (205, 1, 'rich', 'Charles', 'Rich', 'rich@wpi.edu', false, 'password', '1010102', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (206, 1, 'cshue', 'Craig', 'Shue', 'cshue@wpi.edu', false, 'password', '1010103', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (207, 1, 'cpinciroli', 'Carlo', 'Pinciroli', 'cpinciroli@wpi.edu', false, 'password', '1010104', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (208, 1, 'dd', 'Dan', 'Dougherty', 'dd@wpi.edu', false, 'password', '1928347012', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (209, 1, 'dkorkin', 'Dmitry', 'Korkin', 'dkorkin@wpi.edu', false, 'password', '19203847', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (210, 1, 'deslent', 'Doug', 'Selent', 'dselent@wpi.edu', false, 'password', '1283479872', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (211, 1, 'rundenst', 'Elke', 'Rundensteiner', 'rundenst@wpi.edu', false, 'password', '12938476192', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (212, 1, 'lth', 'Lane', 'Harrison', 'lth@wpi.edu', false, 'password', '101', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (213, 1, 'mkh', 'Micha', 'Hofri', 'mkh@wpi.edu', false, 'password', '102', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (214, 1, 'mlc', 'Mark', 'Claypool', 'mlc@wpi.edu', false, 'password', '103', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (215, 1, 'mxc', 'Mike1', 'Ciaraldi', 'mxc@wpi.edu', false, 'password', '104', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (216, 1, 'mxg', 'Mike2', 'Gennert', 'mxg@wpi.edu', false, 'password', '105', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (217, 1, 'mye', 'Mohamed', 'Eltabakh', 'mye@wpi.edu', false, 'password', '106', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (218, 1, 'nth', 'Neil', 'Heffernan', 'nth@wpi.edu', false, 'password', '107', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (219, 1, 'kpray', 'Keith', 'Pray', 'kpray@wpi.edu', false, 'password', '1000', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (220, 1, 'kvenkatasubramanian', 'Krishna', 'Venkatasubramanian', 'kvenkatasubr@wpi.edu', false, 'password', '1001', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (221, 1, 'jwhitehill', 'Jacob', 'Whitehill', 'jwhitehill@wpi.edu', false, 'password', '1002', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (222, 1, 'jtoro', 'Jorge', 'Toro', 'jtoro@wpi.edu', false, 'password', '1003', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (223, 1, 'jsingh', 'Jitendra', 'Singh', 'jsingh@wpi.edu', false, 'password', '1004', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (224, 1, 'jcuneo', 'Joshua', 'Cuneo', 'jcuneo@wpi.edu', false, 'password', '1005', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (225, 1, 'jbreecher', 'Jerry', 'Breecher', 'jbreecher@wpi.edu', false, 'password', '1006', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (226, 1, 'jbeck', 'Joseph "Joe"', 'Beck', 'jbeck@wpi.edu', false, 'password', '1007', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (227, 1, 'yl2', 'Yanhau', 'Li', 'yli@wpi.edu', false, 'password', '200', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (228, 1, 'xk1', 'Xiangnan', 'Kong', 'xkong@wpi.edu', false, 'password', '199', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (229, 1, 'w1w', 'Wilson', 'Wong', 'wwong2@wpi.edu', false, 'password', '198', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (230, 1, 'tg1', 'Tian', 'Guo', 'tian@wpi.edu', false, 'password', '197', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (231, 1, 'sl4', 'Susan', 'Landu', 'slandau@wpi.edu', false, 'password', '196', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (232, 1, 'sim', 'Suzanne', 'Mell-Stark', 'smellostark@wpi.edu', false, 'password', '195', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (233, 1, 'rn1', 'Rodica', 'Neamtu', 'RNeamtu@wpi.edu', false, 'password', '194', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (234, 1, 'rjw', 'Robert', 'Walls', 'rwalls@wpi.edu', false, 'password', '193', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');
INSERT INTO user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) VALUES (203, 1, 'cngan', 'Chun-Kit', 'Ngan', 'cngan@wpi.edu', false, 'password', '6', 1, '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05', '2018-01-24 21:48:57.885059-05');


--
-- TOC entry 2969 (class 0 OID 17473)
-- Dependencies: 218
-- Data for Name: user_info_history; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2966 (class 0 OID 17309)
-- Dependencies: 215
-- Data for Name: wishlist_links; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 198
-- Name: calendar_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('calendar_info_id_seq', 239, true);


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 200
-- Name: course_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('course_info_id_seq', 106, true);


--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 219
-- Name: instructor_info_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('instructor_info_history_id_seq', 5, true);


--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 202
-- Name: instructor_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('instructor_info_id_seq', 44, true);


--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 204
-- Name: lab_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('lab_info_id_seq', 70, true);


--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 206
-- Name: notifications_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('notifications_id_seq', 1, false);


--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 208
-- Name: schedule_links_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('schedule_links_id_seq', 1, false);


--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 210
-- Name: section_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('section_info_id_seq', 142, true);


--
-- TOC entry 3000 (class 0 OID 0)
-- Dependencies: 212
-- Name: section_population_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('section_population_id_seq', 1, false);


--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_info_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_info_history_id_seq', 1, false);


--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 214
-- Name: user_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_info_id_seq', 235, true);


--
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 216
-- Name: wishlist_links_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wishlist_links_id_seq', 1, false);


--
-- TOC entry 2769 (class 2606 OID 17325)
-- Name: calendar_info calendar_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY calendar_info
    ADD CONSTRAINT calendar_info_pkey PRIMARY KEY (id);


--
-- TOC entry 2771 (class 2606 OID 17327)
-- Name: course_info course_info_course_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_info
    ADD CONSTRAINT course_info_course_name_key UNIQUE (course_name);


--
-- TOC entry 2773 (class 2606 OID 17329)
-- Name: course_info course_info_deleted_course_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_info
    ADD CONSTRAINT course_info_deleted_course_name_key UNIQUE (deleted, course_name);


--
-- TOC entry 2775 (class 2606 OID 17331)
-- Name: course_info course_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_info
    ADD CONSTRAINT course_info_pkey PRIMARY KEY (id);


--
-- TOC entry 2807 (class 2606 OID 17500)
-- Name: instructor_info_history instructor_info_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY instructor_info_history
    ADD CONSTRAINT instructor_info_history_pkey PRIMARY KEY (id);


--
-- TOC entry 2777 (class 2606 OID 17333)
-- Name: instructor_info instructor_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY instructor_info
    ADD CONSTRAINT instructor_info_pkey PRIMARY KEY (id);


--
-- TOC entry 2779 (class 2606 OID 17335)
-- Name: lab_info lab_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lab_info
    ADD CONSTRAINT lab_info_pkey PRIMARY KEY (id);


--
-- TOC entry 2781 (class 2606 OID 17337)
-- Name: notifications notifications_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (id);


--
-- TOC entry 2783 (class 2606 OID 17339)
-- Name: schedule_links schedule_links_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule_links
    ADD CONSTRAINT schedule_links_pkey PRIMARY KEY (id);


--
-- TOC entry 2785 (class 2606 OID 17341)
-- Name: section_info section_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY section_info
    ADD CONSTRAINT section_info_pkey PRIMARY KEY (id);


--
-- TOC entry 2787 (class 2606 OID 17343)
-- Name: section_population section_population_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY section_population
    ADD CONSTRAINT section_population_pkey PRIMARY KEY (id);


--
-- TOC entry 2789 (class 2606 OID 17345)
-- Name: user_info user_info_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_email_key UNIQUE (email);


--
-- TOC entry 2791 (class 2606 OID 17347)
-- Name: user_info user_info_first_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_first_name_key UNIQUE (first_name);


--
-- TOC entry 2805 (class 2606 OID 17484)
-- Name: user_info_history user_info_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info_history
    ADD CONSTRAINT user_info_history_pkey PRIMARY KEY (id);


--
-- TOC entry 2793 (class 2606 OID 17349)
-- Name: user_info user_info_last_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_last_name_key UNIQUE (last_name);


--
-- TOC entry 2795 (class 2606 OID 17351)
-- Name: user_info user_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_pkey PRIMARY KEY (id);


--
-- TOC entry 2797 (class 2606 OID 17353)
-- Name: user_info user_info_salt_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_salt_key UNIQUE (salt);


--
-- TOC entry 2799 (class 2606 OID 17355)
-- Name: user_info user_info_username_deleted_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_username_deleted_key UNIQUE (username, deleted);


--
-- TOC entry 2801 (class 2606 OID 17357)
-- Name: user_info user_info_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_username_key UNIQUE (username);


--
-- TOC entry 2803 (class 2606 OID 17359)
-- Name: wishlist_links wishlist_links_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wishlist_links
    ADD CONSTRAINT wishlist_links_pkey PRIMARY KEY (id);


--
-- TOC entry 2825 (class 2620 OID 17514)
-- Name: instructor_info update_instructor_info; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER update_instructor_info BEFORE UPDATE ON instructor_info FOR EACH ROW EXECUTE PROCEDURE insert_instructor_info_history();


--
-- TOC entry 2826 (class 2620 OID 17512)
-- Name: user_info update_user_info; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER update_user_info BEFORE UPDATE ON user_info FOR EACH ROW EXECUTE PROCEDURE insert_user_info_history();


--
-- TOC entry 2823 (class 2606 OID 17501)
-- Name: instructor_info_history instructor_info_history_instructor_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY instructor_info_history
    ADD CONSTRAINT instructor_info_history_instructor_info_id_fkey FOREIGN KEY (instructor_info_id) REFERENCES instructor_info(id) ON DELETE CASCADE;


--
-- TOC entry 2824 (class 2606 OID 17520)
-- Name: instructor_info_history instructor_info_history_user_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY instructor_info_history
    ADD CONSTRAINT instructor_info_history_user_info_id_fkey FOREIGN KEY (user_info_id) REFERENCES user_info(id) ON DELETE CASCADE;


--
-- TOC entry 2808 (class 2606 OID 17360)
-- Name: instructor_info instructor_info_user_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY instructor_info
    ADD CONSTRAINT instructor_info_user_info_id_fkey FOREIGN KEY (user_info_id) REFERENCES user_info(id);


--
-- TOC entry 2809 (class 2606 OID 17365)
-- Name: lab_info lab_info_calendar_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lab_info
    ADD CONSTRAINT lab_info_calendar_info_id_fkey FOREIGN KEY (calendar_info_id) REFERENCES calendar_info(id);


--
-- TOC entry 2810 (class 2606 OID 17370)
-- Name: lab_info lab_info_instructor_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lab_info
    ADD CONSTRAINT lab_info_instructor_info_id_fkey FOREIGN KEY (instructor_info_id) REFERENCES instructor_info(id);


--
-- TOC entry 2811 (class 2606 OID 17375)
-- Name: lab_info lab_info_section_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lab_info
    ADD CONSTRAINT lab_info_section_info_id_fkey FOREIGN KEY (section_info_id) REFERENCES section_info(id);


--
-- TOC entry 2812 (class 2606 OID 17380)
-- Name: notifications notifications_from_user_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notifications
    ADD CONSTRAINT notifications_from_user_info_id_fkey FOREIGN KEY (from_user_info_id) REFERENCES user_info(id);


--
-- TOC entry 2813 (class 2606 OID 17385)
-- Name: notifications notifications_to_user_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notifications
    ADD CONSTRAINT notifications_to_user_info_id_fkey FOREIGN KEY (to_user_info_id) REFERENCES user_info(id);


--
-- TOC entry 2814 (class 2606 OID 17390)
-- Name: schedule_links schedule_links_instructor_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule_links
    ADD CONSTRAINT schedule_links_instructor_info_id_fkey FOREIGN KEY (instructor_info_id) REFERENCES instructor_info(id);


--
-- TOC entry 2815 (class 2606 OID 17395)
-- Name: schedule_links schedule_links_section_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule_links
    ADD CONSTRAINT schedule_links_section_info_id_fkey FOREIGN KEY (section_info_id) REFERENCES section_info(id);


--
-- TOC entry 2816 (class 2606 OID 17400)
-- Name: section_info section_info_calendar_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY section_info
    ADD CONSTRAINT section_info_calendar_info_id_fkey FOREIGN KEY (calendar_info_id) REFERENCES calendar_info(id);


--
-- TOC entry 2817 (class 2606 OID 17405)
-- Name: section_info section_info_course_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY section_info
    ADD CONSTRAINT section_info_course_info_id_fkey FOREIGN KEY (course_info_id) REFERENCES course_info(id);


--
-- TOC entry 2818 (class 2606 OID 17410)
-- Name: section_info section_info_instructor_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY section_info
    ADD CONSTRAINT section_info_instructor_info_id_fkey FOREIGN KEY (instructor_info_id) REFERENCES instructor_info(id);


--
-- TOC entry 2819 (class 2606 OID 17415)
-- Name: section_population section_population_section_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY section_population
    ADD CONSTRAINT section_population_section_info_id_fkey FOREIGN KEY (section_info_id) REFERENCES section_info(id);


--
-- TOC entry 2822 (class 2606 OID 17485)
-- Name: user_info_history user_info_history_user_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info_history
    ADD CONSTRAINT user_info_history_user_info_id_fkey FOREIGN KEY (user_info_id) REFERENCES user_info(id) ON DELETE CASCADE;


--
-- TOC entry 2820 (class 2606 OID 17420)
-- Name: wishlist_links wishlist_links_instructor_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wishlist_links
    ADD CONSTRAINT wishlist_links_instructor_info_id_fkey FOREIGN KEY (instructor_info_id) REFERENCES instructor_info(id);


--
-- TOC entry 2821 (class 2606 OID 17425)
-- Name: wishlist_links wishlist_links_section_info_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wishlist_links
    ADD CONSTRAINT wishlist_links_section_info_id_fkey FOREIGN KEY (section_info_id) REFERENCES section_info(id);


-- Completed on 2018-02-13 10:15:42

--
-- PostgreSQL database dump complete
--

