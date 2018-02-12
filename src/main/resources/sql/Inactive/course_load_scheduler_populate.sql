PGDMP     '    8                v            Course Scheduling System    10.1    10.0 z    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    17230    Course Scheduling System    DATABASE     �   CREATE DATABASE "Course Scheduling System" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
 *   DROP DATABASE "Course Scheduling System";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    4                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    2                        3079    17231 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                  false            �           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                       false    1            �            1255    17513     insert_instructor_info_history()    FUNCTION     a  CREATE FUNCTION insert_instructor_info_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
INSERT INTO instructor_info_history(instructor_info_id, rank, course_load, phone_number, office, user_info_id, department)
VALUES(OLD.id, OLD.rank, OLD.course_load, OLD.phone_number, OLD.office, OLD.user_info_id, OLD.department);
RETURN NEW;
END;
$$;
 7   DROP FUNCTION public.insert_instructor_info_history();
       public       postgres    false    2    4            �            1255    17511    insert_user_info_history()    FUNCTION     �  CREATE FUNCTION insert_user_info_history() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
INSERT INTO user_info_history(user_info_id, user_role, username, first_name, last_name, email, encrypted_password, salt, account_state)
VALUES(OLD.id, OLD.user_role, OLD.username, OLD.first_name, OLD.last_name, OLD.email, OLD.encrypted_password, OLD.salt, OLD.account_state);
RETURN NEW;
END;
$$;
 1   DROP FUNCTION public.insert_user_info_history();
       public       postgres    false    2    4            �            1259    17240    calendar_info    TABLE       CREATE TABLE calendar_info (
    id integer NOT NULL,
    year integer NOT NULL,
    semester character varying(255) NOT NULL,
    days character varying(255) NOT NULL,
    start_time integer NOT NULL,
    end_time integer NOT NULL,
    term integer NOT NULL
);
 !   DROP TABLE public.calendar_info;
       public         postgres    false    4            �            1259    17246    calendar_info_id_seq    SEQUENCE     �   CREATE SEQUENCE calendar_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.calendar_info_id_seq;
       public       postgres    false    197    4            �           0    0    calendar_info_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE calendar_info_id_seq OWNED BY calendar_info.id;
            public       postgres    false    198            �            1259    17248    course_info    TABLE     �  CREATE TABLE course_info (
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
    DROP TABLE public.course_info;
       public         postgres    false    4            �            1259    17255    course_info_id_seq    SEQUENCE     �   CREATE SEQUENCE course_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.course_info_id_seq;
       public       postgres    false    4    199            �           0    0    course_info_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE course_info_id_seq OWNED BY course_info.id;
            public       postgres    false    200            �            1259    17257    instructor_info    TABLE       CREATE TABLE instructor_info (
    id integer NOT NULL,
    rank integer NOT NULL,
    course_load integer NOT NULL,
    phone_number integer NOT NULL,
    office character varying(255),
    user_info_id integer,
    department character varying(255) NOT NULL
);
 #   DROP TABLE public.instructor_info;
       public         postgres    false    4            �            1259    17492    instructor_info_history    TABLE     A  CREATE TABLE instructor_info_history (
    id integer NOT NULL,
    instructor_info_id integer NOT NULL,
    rank integer NOT NULL,
    course_load integer NOT NULL,
    phone_number integer NOT NULL,
    office character varying(255) NOT NULL,
    user_info_id integer,
    department character varying(255) NOT NULL
);
 +   DROP TABLE public.instructor_info_history;
       public         postgres    false    4            �            1259    17490    instructor_info_history_id_seq    SEQUENCE     �   CREATE SEQUENCE instructor_info_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.instructor_info_history_id_seq;
       public       postgres    false    220    4            �           0    0    instructor_info_history_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE instructor_info_history_id_seq OWNED BY instructor_info_history.id;
            public       postgres    false    219            �            1259    17263    instructor_info_id_seq    SEQUENCE     �   CREATE SEQUENCE instructor_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.instructor_info_id_seq;
       public       postgres    false    4    201            �           0    0    instructor_info_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE instructor_info_id_seq OWNED BY instructor_info.id;
            public       postgres    false    202            �            1259    17265    lab_info    TABLE     �   CREATE TABLE lab_info (
    id integer NOT NULL,
    section_info_id integer,
    instructor_info_id integer,
    location character varying(255) NOT NULL,
    calendar_info_id integer
);
    DROP TABLE public.lab_info;
       public         postgres    false    4            �            1259    17268    lab_info_id_seq    SEQUENCE     �   CREATE SEQUENCE lab_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.lab_info_id_seq;
       public       postgres    false    203    4            �           0    0    lab_info_id_seq    SEQUENCE OWNED BY     5   ALTER SEQUENCE lab_info_id_seq OWNED BY lab_info.id;
            public       postgres    false    204            �            1259    17270    notifications    TABLE     �   CREATE TABLE notifications (
    id integer NOT NULL,
    message character varying(2000),
    from_user_info_id integer,
    to_user_info_id integer
);
 !   DROP TABLE public.notifications;
       public         postgres    false    4            �            1259    17276    notifications_id_seq    SEQUENCE     �   CREATE SEQUENCE notifications_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.notifications_id_seq;
       public       postgres    false    205    4            �           0    0    notifications_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE notifications_id_seq OWNED BY notifications.id;
            public       postgres    false    206            �            1259    17278    schedule_links    TABLE     v   CREATE TABLE schedule_links (
    id integer NOT NULL,
    instructor_info_id integer,
    section_info_id integer
);
 "   DROP TABLE public.schedule_links;
       public         postgres    false    4            �            1259    17281    schedule_links_id_seq    SEQUENCE     �   CREATE SEQUENCE schedule_links_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.schedule_links_id_seq;
       public       postgres    false    207    4            �           0    0    schedule_links_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE schedule_links_id_seq OWNED BY schedule_links.id;
            public       postgres    false    208            �            1259    17283    section_info    TABLE     B  CREATE TABLE section_info (
    id integer NOT NULL,
    section_number integer NOT NULL,
    section_type character varying(255) NOT NULL,
    instructor_info_id integer,
    location character varying(255) NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    course_info_id integer,
    calendar_info_id integer
);
     DROP TABLE public.section_info;
       public         postgres    false    4            �            1259    17290    section_info_id_seq    SEQUENCE     �   CREATE SEQUENCE section_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.section_info_id_seq;
       public       postgres    false    4    209            �           0    0    section_info_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE section_info_id_seq OWNED BY section_info.id;
            public       postgres    false    210            �            1259    17292    section_population    TABLE     �   CREATE TABLE section_population (
    id integer NOT NULL,
    expected_population integer NOT NULL,
    popoulation_cap integer NOT NULL,
    section_info_id integer
);
 &   DROP TABLE public.section_population;
       public         postgres    false    4            �            1259    17295    section_population_id_seq    SEQUENCE     �   CREATE SEQUENCE section_population_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.section_population_id_seq;
       public       postgres    false    211    4            �           0    0    section_population_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE section_population_id_seq OWNED BY section_population.id;
            public       postgres    false    212            �            1259    17297 	   user_info    TABLE     �  CREATE TABLE user_info (
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
    DROP TABLE public.user_info;
       public         postgres    false    4            �            1259    17473    user_info_history    TABLE     �  CREATE TABLE user_info_history (
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
 %   DROP TABLE public.user_info_history;
       public         postgres    false    4            �            1259    17471    user_info_history_id_seq    SEQUENCE     �   CREATE SEQUENCE user_info_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.user_info_history_id_seq;
       public       postgres    false    4    218            �           0    0    user_info_history_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE user_info_history_id_seq OWNED BY user_info_history.id;
            public       postgres    false    217            �            1259    17307    user_info_id_seq    SEQUENCE     �   CREATE SEQUENCE user_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.user_info_id_seq;
       public       postgres    false    213    4            �           0    0    user_info_id_seq    SEQUENCE OWNED BY     7   ALTER SEQUENCE user_info_id_seq OWNED BY user_info.id;
            public       postgres    false    214            �            1259    17309    wishlist_links    TABLE     v   CREATE TABLE wishlist_links (
    id integer NOT NULL,
    instructor_info_id integer,
    section_info_id integer
);
 "   DROP TABLE public.wishlist_links;
       public         postgres    false    4            �            1259    17312    wishlist_links_id_seq    SEQUENCE     �   CREATE SEQUENCE wishlist_links_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.wishlist_links_id_seq;
       public       postgres    false    4    215            �           0    0    wishlist_links_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE wishlist_links_id_seq OWNED BY wishlist_links.id;
            public       postgres    false    216            �
           2604    17314    calendar_info id    DEFAULT     f   ALTER TABLE ONLY calendar_info ALTER COLUMN id SET DEFAULT nextval('calendar_info_id_seq'::regclass);
 ?   ALTER TABLE public.calendar_info ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    198    197            �
           2604    17315    course_info id    DEFAULT     b   ALTER TABLE ONLY course_info ALTER COLUMN id SET DEFAULT nextval('course_info_id_seq'::regclass);
 =   ALTER TABLE public.course_info ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    200    199            �
           2604    17316    instructor_info id    DEFAULT     j   ALTER TABLE ONLY instructor_info ALTER COLUMN id SET DEFAULT nextval('instructor_info_id_seq'::regclass);
 A   ALTER TABLE public.instructor_info ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    202    201            �
           2604    17495    instructor_info_history id    DEFAULT     z   ALTER TABLE ONLY instructor_info_history ALTER COLUMN id SET DEFAULT nextval('instructor_info_history_id_seq'::regclass);
 I   ALTER TABLE public.instructor_info_history ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    220    219    220            �
           2604    17317    lab_info id    DEFAULT     \   ALTER TABLE ONLY lab_info ALTER COLUMN id SET DEFAULT nextval('lab_info_id_seq'::regclass);
 :   ALTER TABLE public.lab_info ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    204    203            �
           2604    17318    notifications id    DEFAULT     f   ALTER TABLE ONLY notifications ALTER COLUMN id SET DEFAULT nextval('notifications_id_seq'::regclass);
 ?   ALTER TABLE public.notifications ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    206    205            �
           2604    17319    schedule_links id    DEFAULT     h   ALTER TABLE ONLY schedule_links ALTER COLUMN id SET DEFAULT nextval('schedule_links_id_seq'::regclass);
 @   ALTER TABLE public.schedule_links ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    208    207            �
           2604    17320    section_info id    DEFAULT     d   ALTER TABLE ONLY section_info ALTER COLUMN id SET DEFAULT nextval('section_info_id_seq'::regclass);
 >   ALTER TABLE public.section_info ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    210    209            �
           2604    17321    section_population id    DEFAULT     p   ALTER TABLE ONLY section_population ALTER COLUMN id SET DEFAULT nextval('section_population_id_seq'::regclass);
 D   ALTER TABLE public.section_population ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    212    211            �
           2604    17322    user_info id    DEFAULT     ^   ALTER TABLE ONLY user_info ALTER COLUMN id SET DEFAULT nextval('user_info_id_seq'::regclass);
 ;   ALTER TABLE public.user_info ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    214    213            �
           2604    17476    user_info_history id    DEFAULT     n   ALTER TABLE ONLY user_info_history ALTER COLUMN id SET DEFAULT nextval('user_info_history_id_seq'::regclass);
 C   ALTER TABLE public.user_info_history ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    217    218    218            �
           2604    17323    wishlist_links id    DEFAULT     h   ALTER TABLE ONLY wishlist_links ALTER COLUMN id SET DEFAULT nextval('wishlist_links_id_seq'::regclass);
 @   ALTER TABLE public.wishlist_links ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    216    215            �          0    17240    calendar_info 
   TABLE DATA               V   COPY calendar_info (id, year, semester, days, start_time, end_time, term) FROM stdin;
    public       postgres    false    197   ��       �          0    17248    course_info 
   TABLE DATA               �   COPY course_info (id, course_name, required_frequency_per_term, required_frequency_per_semester, required_frequency_per_year, credit_amount, deleted, department, course_number) FROM stdin;
    public       postgres    false    199   ٞ       �          0    17257    instructor_info 
   TABLE DATA               i   COPY instructor_info (id, rank, course_load, phone_number, office, user_info_id, department) FROM stdin;
    public       postgres    false    201   �       �          0    17492    instructor_info_history 
   TABLE DATA               �   COPY instructor_info_history (id, instructor_info_id, rank, course_load, phone_number, office, user_info_id, department) FROM stdin;
    public       postgres    false    220   �       �          0    17265    lab_info 
   TABLE DATA               `   COPY lab_info (id, section_info_id, instructor_info_id, location, calendar_info_id) FROM stdin;
    public       postgres    false    203   �       �          0    17270    notifications 
   TABLE DATA               Q   COPY notifications (id, message, from_user_info_id, to_user_info_id) FROM stdin;
    public       postgres    false    205   ��       �          0    17278    schedule_links 
   TABLE DATA               J   COPY schedule_links (id, instructor_info_id, section_info_id) FROM stdin;
    public       postgres    false    207   ��       �          0    17283    section_info 
   TABLE DATA               �   COPY section_info (id, section_number, section_type, instructor_info_id, location, deleted, course_info_id, calendar_info_id) FROM stdin;
    public       postgres    false    209   ˥       �          0    17292    section_population 
   TABLE DATA               `   COPY section_population (id, expected_population, popoulation_cap, section_info_id) FROM stdin;
    public       postgres    false    211   %�       �          0    17297 	   user_info 
   TABLE DATA               �   COPY user_info (id, user_role, username, first_name, last_name, email, deleted, encrypted_password, salt, account_state, created_at, updated_at, login_time) FROM stdin;
    public       postgres    false    213   B�       �          0    17473    user_info_history 
   TABLE DATA               �   COPY user_info_history (id, user_info_id, user_role, username, first_name, last_name, email, encrypted_password, salt, account_state, created_at, updated_at, login_time) FROM stdin;
    public       postgres    false    218   ��       �          0    17309    wishlist_links 
   TABLE DATA               J   COPY wishlist_links (id, instructor_info_id, section_info_id) FROM stdin;
    public       postgres    false    215   ��       �           0    0    calendar_info_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('calendar_info_id_seq', 239, true);
            public       postgres    false    198            �           0    0    course_info_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('course_info_id_seq', 106, true);
            public       postgres    false    200            �           0    0    instructor_info_history_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('instructor_info_history_id_seq', 5, true);
            public       postgres    false    219            �           0    0    instructor_info_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('instructor_info_id_seq', 44, true);
            public       postgres    false    202            �           0    0    lab_info_id_seq    SEQUENCE SET     7   SELECT pg_catalog.setval('lab_info_id_seq', 70, true);
            public       postgres    false    204            �           0    0    notifications_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('notifications_id_seq', 1, false);
            public       postgres    false    206            �           0    0    schedule_links_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('schedule_links_id_seq', 1, false);
            public       postgres    false    208            �           0    0    section_info_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('section_info_id_seq', 142, true);
            public       postgres    false    210            �           0    0    section_population_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('section_population_id_seq', 1, false);
            public       postgres    false    212            �           0    0    user_info_history_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('user_info_history_id_seq', 1, false);
            public       postgres    false    217            �           0    0    user_info_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('user_info_id_seq', 235, true);
            public       postgres    false    214            �           0    0    wishlist_links_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('wishlist_links_id_seq', 1, false);
            public       postgres    false    216            �
           2606    17325     calendar_info calendar_info_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY calendar_info
    ADD CONSTRAINT calendar_info_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.calendar_info DROP CONSTRAINT calendar_info_pkey;
       public         postgres    false    197            �
           2606    17327 '   course_info course_info_course_name_key 
   CONSTRAINT     b   ALTER TABLE ONLY course_info
    ADD CONSTRAINT course_info_course_name_key UNIQUE (course_name);
 Q   ALTER TABLE ONLY public.course_info DROP CONSTRAINT course_info_course_name_key;
       public         postgres    false    199            �
           2606    17329 /   course_info course_info_deleted_course_name_key 
   CONSTRAINT     s   ALTER TABLE ONLY course_info
    ADD CONSTRAINT course_info_deleted_course_name_key UNIQUE (deleted, course_name);
 Y   ALTER TABLE ONLY public.course_info DROP CONSTRAINT course_info_deleted_course_name_key;
       public         postgres    false    199    199            �
           2606    17331    course_info course_info_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY course_info
    ADD CONSTRAINT course_info_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.course_info DROP CONSTRAINT course_info_pkey;
       public         postgres    false    199            �
           2606    17500 4   instructor_info_history instructor_info_history_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY instructor_info_history
    ADD CONSTRAINT instructor_info_history_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.instructor_info_history DROP CONSTRAINT instructor_info_history_pkey;
       public         postgres    false    220            �
           2606    17333 $   instructor_info instructor_info_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY instructor_info
    ADD CONSTRAINT instructor_info_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.instructor_info DROP CONSTRAINT instructor_info_pkey;
       public         postgres    false    201            �
           2606    17335    lab_info lab_info_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY lab_info
    ADD CONSTRAINT lab_info_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.lab_info DROP CONSTRAINT lab_info_pkey;
       public         postgres    false    203            �
           2606    17337     notifications notifications_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.notifications DROP CONSTRAINT notifications_pkey;
       public         postgres    false    205            �
           2606    17339 "   schedule_links schedule_links_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY schedule_links
    ADD CONSTRAINT schedule_links_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.schedule_links DROP CONSTRAINT schedule_links_pkey;
       public         postgres    false    207            �
           2606    17341    section_info section_info_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY section_info
    ADD CONSTRAINT section_info_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.section_info DROP CONSTRAINT section_info_pkey;
       public         postgres    false    209            �
           2606    17343 *   section_population section_population_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY section_population
    ADD CONSTRAINT section_population_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.section_population DROP CONSTRAINT section_population_pkey;
       public         postgres    false    211            �
           2606    17345    user_info user_info_email_key 
   CONSTRAINT     R   ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_email_key UNIQUE (email);
 G   ALTER TABLE ONLY public.user_info DROP CONSTRAINT user_info_email_key;
       public         postgres    false    213            �
           2606    17347 "   user_info user_info_first_name_key 
   CONSTRAINT     \   ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_first_name_key UNIQUE (first_name);
 L   ALTER TABLE ONLY public.user_info DROP CONSTRAINT user_info_first_name_key;
       public         postgres    false    213            �
           2606    17484 (   user_info_history user_info_history_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY user_info_history
    ADD CONSTRAINT user_info_history_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.user_info_history DROP CONSTRAINT user_info_history_pkey;
       public         postgres    false    218            �
           2606    17349 !   user_info user_info_last_name_key 
   CONSTRAINT     Z   ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_last_name_key UNIQUE (last_name);
 K   ALTER TABLE ONLY public.user_info DROP CONSTRAINT user_info_last_name_key;
       public         postgres    false    213            �
           2606    17351    user_info user_info_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.user_info DROP CONSTRAINT user_info_pkey;
       public         postgres    false    213            �
           2606    17353    user_info user_info_salt_key 
   CONSTRAINT     P   ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_salt_key UNIQUE (salt);
 F   ALTER TABLE ONLY public.user_info DROP CONSTRAINT user_info_salt_key;
       public         postgres    false    213            �
           2606    17355 (   user_info user_info_username_deleted_key 
   CONSTRAINT     i   ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_username_deleted_key UNIQUE (username, deleted);
 R   ALTER TABLE ONLY public.user_info DROP CONSTRAINT user_info_username_deleted_key;
       public         postgres    false    213    213            �
           2606    17357     user_info user_info_username_key 
   CONSTRAINT     X   ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_username_key UNIQUE (username);
 J   ALTER TABLE ONLY public.user_info DROP CONSTRAINT user_info_username_key;
       public         postgres    false    213            �
           2606    17359 "   wishlist_links wishlist_links_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY wishlist_links
    ADD CONSTRAINT wishlist_links_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.wishlist_links DROP CONSTRAINT wishlist_links_pkey;
       public         postgres    false    215            	           2620    17514 &   instructor_info update_instructor_info    TRIGGER     �   CREATE TRIGGER update_instructor_info BEFORE UPDATE ON instructor_info FOR EACH ROW EXECUTE PROCEDURE insert_instructor_info_history();
 ?   DROP TRIGGER update_instructor_info ON public.instructor_info;
       public       postgres    false    231    201            
           2620    17512    user_info update_user_info    TRIGGER     v   CREATE TRIGGER update_user_info BEFORE UPDATE ON user_info FOR EACH ROW EXECUTE PROCEDURE insert_user_info_history();
 3   DROP TRIGGER update_user_info ON public.user_info;
       public       postgres    false    228    213                       2606    17501 G   instructor_info_history instructor_info_history_instructor_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY instructor_info_history
    ADD CONSTRAINT instructor_info_history_instructor_info_id_fkey FOREIGN KEY (instructor_info_id) REFERENCES instructor_info(id) ON DELETE CASCADE;
 q   ALTER TABLE ONLY public.instructor_info_history DROP CONSTRAINT instructor_info_history_instructor_info_id_fkey;
       public       postgres    false    2777    220    201                       2606    17520 A   instructor_info_history instructor_info_history_user_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY instructor_info_history
    ADD CONSTRAINT instructor_info_history_user_info_id_fkey FOREIGN KEY (user_info_id) REFERENCES user_info(id) ON DELETE CASCADE;
 k   ALTER TABLE ONLY public.instructor_info_history DROP CONSTRAINT instructor_info_history_user_info_id_fkey;
       public       postgres    false    2795    220    213            �
           2606    17360 1   instructor_info instructor_info_user_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY instructor_info
    ADD CONSTRAINT instructor_info_user_info_id_fkey FOREIGN KEY (user_info_id) REFERENCES user_info(id);
 [   ALTER TABLE ONLY public.instructor_info DROP CONSTRAINT instructor_info_user_info_id_fkey;
       public       postgres    false    213    2795    201            �
           2606    17365 '   lab_info lab_info_calendar_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY lab_info
    ADD CONSTRAINT lab_info_calendar_info_id_fkey FOREIGN KEY (calendar_info_id) REFERENCES calendar_info(id);
 Q   ALTER TABLE ONLY public.lab_info DROP CONSTRAINT lab_info_calendar_info_id_fkey;
       public       postgres    false    2769    203    197            �
           2606    17370 )   lab_info lab_info_instructor_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY lab_info
    ADD CONSTRAINT lab_info_instructor_info_id_fkey FOREIGN KEY (instructor_info_id) REFERENCES instructor_info(id);
 S   ALTER TABLE ONLY public.lab_info DROP CONSTRAINT lab_info_instructor_info_id_fkey;
       public       postgres    false    2777    201    203            �
           2606    17375 &   lab_info lab_info_section_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY lab_info
    ADD CONSTRAINT lab_info_section_info_id_fkey FOREIGN KEY (section_info_id) REFERENCES section_info(id);
 P   ALTER TABLE ONLY public.lab_info DROP CONSTRAINT lab_info_section_info_id_fkey;
       public       postgres    false    203    209    2785            �
           2606    17380 2   notifications notifications_from_user_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY notifications
    ADD CONSTRAINT notifications_from_user_info_id_fkey FOREIGN KEY (from_user_info_id) REFERENCES user_info(id);
 \   ALTER TABLE ONLY public.notifications DROP CONSTRAINT notifications_from_user_info_id_fkey;
       public       postgres    false    2795    205    213            �
           2606    17385 0   notifications notifications_to_user_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY notifications
    ADD CONSTRAINT notifications_to_user_info_id_fkey FOREIGN KEY (to_user_info_id) REFERENCES user_info(id);
 Z   ALTER TABLE ONLY public.notifications DROP CONSTRAINT notifications_to_user_info_id_fkey;
       public       postgres    false    213    205    2795            �
           2606    17390 5   schedule_links schedule_links_instructor_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY schedule_links
    ADD CONSTRAINT schedule_links_instructor_info_id_fkey FOREIGN KEY (instructor_info_id) REFERENCES instructor_info(id);
 _   ALTER TABLE ONLY public.schedule_links DROP CONSTRAINT schedule_links_instructor_info_id_fkey;
       public       postgres    false    2777    201    207            �
           2606    17395 2   schedule_links schedule_links_section_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY schedule_links
    ADD CONSTRAINT schedule_links_section_info_id_fkey FOREIGN KEY (section_info_id) REFERENCES section_info(id);
 \   ALTER TABLE ONLY public.schedule_links DROP CONSTRAINT schedule_links_section_info_id_fkey;
       public       postgres    false    2785    209    207                        2606    17400 /   section_info section_info_calendar_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY section_info
    ADD CONSTRAINT section_info_calendar_info_id_fkey FOREIGN KEY (calendar_info_id) REFERENCES calendar_info(id);
 Y   ALTER TABLE ONLY public.section_info DROP CONSTRAINT section_info_calendar_info_id_fkey;
       public       postgres    false    2769    197    209                       2606    17405 -   section_info section_info_course_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY section_info
    ADD CONSTRAINT section_info_course_info_id_fkey FOREIGN KEY (course_info_id) REFERENCES course_info(id);
 W   ALTER TABLE ONLY public.section_info DROP CONSTRAINT section_info_course_info_id_fkey;
       public       postgres    false    199    2775    209                       2606    17410 1   section_info section_info_instructor_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY section_info
    ADD CONSTRAINT section_info_instructor_info_id_fkey FOREIGN KEY (instructor_info_id) REFERENCES instructor_info(id);
 [   ALTER TABLE ONLY public.section_info DROP CONSTRAINT section_info_instructor_info_id_fkey;
       public       postgres    false    2777    209    201                       2606    17415 :   section_population section_population_section_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY section_population
    ADD CONSTRAINT section_population_section_info_id_fkey FOREIGN KEY (section_info_id) REFERENCES section_info(id);
 d   ALTER TABLE ONLY public.section_population DROP CONSTRAINT section_population_section_info_id_fkey;
       public       postgres    false    211    2785    209                       2606    17485 5   user_info_history user_info_history_user_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY user_info_history
    ADD CONSTRAINT user_info_history_user_info_id_fkey FOREIGN KEY (user_info_id) REFERENCES user_info(id) ON DELETE CASCADE;
 _   ALTER TABLE ONLY public.user_info_history DROP CONSTRAINT user_info_history_user_info_id_fkey;
       public       postgres    false    2795    218    213                       2606    17420 5   wishlist_links wishlist_links_instructor_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY wishlist_links
    ADD CONSTRAINT wishlist_links_instructor_info_id_fkey FOREIGN KEY (instructor_info_id) REFERENCES instructor_info(id);
 _   ALTER TABLE ONLY public.wishlist_links DROP CONSTRAINT wishlist_links_instructor_info_id_fkey;
       public       postgres    false    215    2777    201                       2606    17425 2   wishlist_links wishlist_links_section_info_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY wishlist_links
    ADD CONSTRAINT wishlist_links_section_info_id_fkey FOREIGN KEY (section_info_id) REFERENCES section_info(id);
 \   ALTER TABLE ONLY public.wishlist_links DROP CONSTRAINT wishlist_links_section_info_id_fkey;
       public       postgres    false    2785    215    209            �   �  x�����1E��A�*?C�EH�#mBB��B���=��Gۓp��q�V�t��o����sy��^���[hņ������t�
a�
�o�W�Wo�Ε���}�iq�Q.}[ Hk�^����)�915H�Z.v�'���r��ܪ%�=VP��Rq٩7Z8��0�Q_�XWH��s`�b��Yt\эeR�vd�����H�z*�݂��:c��<�F�w��TX��4Ư�fR-��T�S))��E� �dF&3�@J���P�穠+�ݰZ^ŦwdE(���qG4�癹��HE��8F�� Ӧ�<l_�Gn�'�jİ��5e�)��q��lr�~�
mT�e�;7V�`G1�!��E3b�0�2�}�����%0�1�C��2���b�ps�h���/�E1��H��0�	��q#%�.ɷ%�ذL�鳔�$q)M�y��Tf��n�i#d����d���M��'��s����o�'�ʱsLYX�i,���9��2|�+�R?;*�~�w%��RLS1&̌�̻�X]}�5k�������s�1��Dn�U��j��r�\T)�9j���e��<JW������a~��)�����'Y��!ݿ�w|���6�5e?G�w�[�Ѩv=��h�T���µ?(zk�8:M�����|eDM�o�v�h�_���ү7yg���i*��Lȋ�sS�=�h9w��I��.��ǅL����D�W��,��f1�<�
S��w��{�����|èqa~�+����J��U><����7Ƨg'0���I"�iRAM��84���2س��g����S�L�4������z�T�V���ۣv�F��+��+&L^��+MS��%պe����>n��ٴ���e�by���՞����4��q��b!���Ɂ�,�;���8fz�����&d�ߓL�	���M�dv����lk#kz-=Ԫ׎_���rHmc��g	��a]����)      �   (  x��Vˮ�6\�_�UWI!�$9;Z�e&��5\d�EZtѢh����P���}o���9�9sF��6��:jҩxD��p�;�bc�!u:��R�8K��ikcr6�H����"q�huM*g+}�	x�)�;Lst�;eӝ��=1�U��"
��y&(�
�2�%����wp��9yW���uIH�1-!�!���D��X�}Brx�	Yp@�P��# H���	�7���)��K0C�m`�x�º{AY��K]� ��zX���b�P��ګ !�u�1[ ����;9��y"��8�������m���|���mdSٚ�t�o/�U��U����f�&(�*Ѹ����kLcg�%}�f�w�TU����J��PJP�g�3�`L���A�E�U�%��X�u�}�>:��g<�C��`-������0D�{շ}x���=��$�?��7dg�%2���(^��mM�a�!���8]5doT���h�aNQ<j�/Iq��t0(L�G�:���~�����$�{ѐ�Xr�ٶ�s��Ƶ��HI�5��^'}�56�eMԹ�i5���{�7��c��!����;q����ց!�y�,�*���)���b%�7��5��Mia����`X��P�C��z6��r+���qA
bٝ)lP�S,(�rY��
k���X����m���qK]pt��df�"ED]m��e\�X|e��,^��ғ�Y���)X>��lM�a ������:On*r�\�����J�3�p:�j+�6�tB/&���:�U��������ǟ�A��|������3�]�W�㦸w�m�* ��z\��-���YV��#wJ��,�a3�������n�=�Ji[����
����G;��彜	��n�X�:�$������-�7�JӼm^���,�y�z�\������<\�lܛ"�Ļ��-�����ݽ;L�$��jU�r�nP���!��p��(��M��p�qѝ�)��|�Wf�O��p���cK��A G}�9;W����<��w���fho���j_ǘI	�8��|A�9��+���X�9      �   �   x�M�+n�@ Q�9Lۛ��KJ{�sT�`=`�}��s�����U&�\�`9V9Y�U.�k���^�ayV�����MbR���V���d*\S�:�J��T�n���0Uo�U��W��^՛zUo�U��W��^՛zUo�U��W���ՇzW��^�zW�]}�w���ՇzW�]}�w���T?�O���T?���x��>�W���Ƕm3���      �      x�3�4BNSN?G�?N�`�=... -��      �   i  x�]�=�1���s���+�L�1cS����#o6��C�3*V��%����0�0�y�[?��y��B]|���U�~\:�%�)�Ϫ	-�q҆N	=��~I�Il�%����<G��6�0k��La��a�l����'��{�[��`�ۄ������9ମ��4x4rx6
x5Jp�q[{r4FX7���O+��fn4���˘���̃� 7�u�!�G�_��&3��N*p��'���zM��qC-�_2_���p٬��x�g�y���T�r3E�y3C��&Ui�����L�,���
��l��׼����J�)ʺʻ9*d�q��O��[���DM�q��Z�8�n�Os�n�� �Y+Ձ      �      x������ � �      �      x������ � �      �   J  x�}W�n9<�_��B$�<&��x����	&�E�����vϴ����Jl�D)f��|����/���{�xǜ�����d 
�nW�y��|zw~y.N��v����#���8v�E�"'	?mhe64�D�٬�r�c�f4��ڗ�\-����ۤA�$~���C�cO��u:/��:&�XHz�`�U�p�("�x�@�H2Iw����S��A4�ɤ� .$��O�]�BZ\�nS�����ya$
~�]�(x�`�POA�9����4[F����u�i�m�8Sh����3�pd
yO��Q��_�d�2 Cݓae$O���peE>8ܳ@Q���vJ��'��`�ťL1l�J1l���s_�>,�R,}ɯ0�PVgJ�;�٨�)�R�E�o�zW������P��jqE)5�V���@)n���R�GJi\]I�L���6[���]��K���@%sНu���3��\4��)��^LY���By��,1��J9|�@9ZM��r��3f$��\d�Ȕ��^�� �:hpBT!�^�G��H���D������@��3���d_�4i-1sH_�4m|�<N���kC�a3�M�#��vZ�J�'h(�����������o��]���ZC�2����P����3�ZC�aԅ!T�D)�*��d4���
�G`��q=��V̛�����e��T��xM�>���7ոN7V���o��v���˷��!T���|��=Yu�g��`j�q��W�W�*;bz�y@P#`��e�*BG�~W��~!::=s#郵�0p���W�M����U�S���cB�@�U���K�ǘ�#�Z�a#��+�z��F_1�C��f�d��m�z� ��q��,���l�n�$�ZU�8(4�t�a������O1�ho���q:�5�oyt�����ayu��	<�%��nl��NW���
�,�e��5�-xX����Ʈ&�M�`彳YPLw4]�΀��^��6d�-k���*+�N�/�0����ަ��+��:(C]q9EM�Nק�|z�S�&J'��i���T���^ٳNC�dTx0�u�I���?�R�};@s2�J���-�{�      �      x������ � �      �   3  x���Mo�8�ϓ_Q��@���[�����t���cѢH���:��3�G�X�9/c�	9z3d>�!�4�v��������~�n�*;x��a�|	y&.�"���Y~.��D�e8�2���G�hr�����Y>8�j�х��C;�~wR{����V��h�3��:��13�篔�
����L9�R���	3ۈ,H_��,�L.���� d�D��Ua��{�̥�G����4��Fv��}|λU�z�3%k��-,b���Y�)��"��(Ge#+Y�7��Ho%<£�\-V.�Q��Z.������D01�Ļ{8��/�U&8�����Z�������Bm�E`�^Q�B2������0�����LI�.�0C�Iz]T0��7*�3��3qg�d�N�kA�(�3q��e�Ѷдw�����)��G��/�ϲ�oh��z����$�D�ë,��J�ϲv����˨�}tP{��x�[P��<�T�(����9�_��&��M�W����+l�lh�A}>DTS��XrB�w��e9)i~�VX?X����L�����<��+x@��0w�^S�S~2���ޘ�_�����9CJOqŌC>��B]��ZziJMR��٬��ȳ�<��%%�3b�!�iv
��J|�Z���K(��xϡ"'7��n=*�>������$�y�ݒ���x��4r=Q7
i���0%�_�)[�V�n��W�]��������{��=J���m�[UaE
��pKx9�C=|̶%h�u뼃�x��A�(� 1����A[<������KX�p��`1�����Eg-���1�k=P�.&h���+U`���c�usO=p̆/��KUԴ`jS}�|��g�!%�=\����vF���V��{�Q���F�����'�Պ����*�L�ɼWh"^�m�xz���p�[|�4�u��t�*����A�{�'�io�¢Ȃ0����@��W���A�w��<(c�-������O�1S���6�gW�BbM#����}�Fb6�A��YoiI�狤�!��&�}�@�v��4�:{~�[x�(j)$ބ������7s�!      �      x������ � �      �      x������ � �     