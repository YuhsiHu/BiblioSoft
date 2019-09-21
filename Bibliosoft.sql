--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

-- Started on 2019-09-22 00:11:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "Bibliosoft";
--
-- TOC entry 2908 (class 1262 OID 25734)
-- Name: Bibliosoft; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "Bibliosoft" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Chinese (Simplified)_China.936' LC_CTYPE = 'Chinese (Simplified)_China.936';


ALTER DATABASE "Bibliosoft" OWNER TO postgres;

\connect "Bibliosoft"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 25735)
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin (
    admin_id integer NOT NULL,
    password character varying(32),
    admin_name character varying(40)
);


ALTER TABLE public.admin OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 25738)
-- Name: admin_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.admin_admin_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_admin_id_seq OWNER TO postgres;

--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 197
-- Name: admin_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.admin_admin_id_seq OWNED BY public.admin.admin_id;


--
-- TOC entry 198 (class 1259 OID 25740)
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book (
    isbn character varying(13) NOT NULL,
    book_name character varying(80) NOT NULL,
    book_description text,
    edition text NOT NULL,
    language character varying(40) NOT NULL,
    publisher_time date NOT NULL,
    price character varying(8) NOT NULL,
    page numeric(5,0) NOT NULL,
    publisher_name text NOT NULL,
    author text NOT NULL,
    catagory text
);


ALTER TABLE public.book OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 25746)
-- Name: bookinlib; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bookinlib (
    book_id integer NOT NULL,
    isbn character varying NOT NULL,
    status character varying(15) NOT NULL,
    location text NOT NULL
);


ALTER TABLE public.bookinlib OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 25752)
-- Name: bookinlib_book_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bookinlib_book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bookinlib_book_id_seq OWNER TO postgres;

--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 200
-- Name: bookinlib_book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bookinlib_book_id_seq OWNED BY public.bookinlib.book_id;


--
-- TOC entry 201 (class 1259 OID 25754)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    category_name character varying(40) NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 25757)
-- Name: default_value; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.default_value (
    longesttime numeric(10,0) NOT NULL,
    fine numeric(10,5) NOT NULL,
    deposit numeric(10,5) NOT NULL,
    reserve_time numeric(10,5) NOT NULL
);


ALTER TABLE public.default_value OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 25760)
-- Name: deletebook; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.deletebook (
    book_id integer NOT NULL,
    lib_id integer NOT NULL
);


ALTER TABLE public.deletebook OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 25763)
-- Name: income; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.income (
    income_id integer NOT NULL,
    income_date date NOT NULL,
    income_value numeric(10,0) NOT NULL,
    income_from text NOT NULL
);


ALTER TABLE public.income OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 25769)
-- Name: income_income_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.income_income_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.income_income_id_seq OWNER TO postgres;

--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 205
-- Name: income_income_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.income_income_id_seq OWNED BY public.income.income_id;


--
-- TOC entry 206 (class 1259 OID 25771)
-- Name: librarian; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.librarian (
    lib_id integer NOT NULL,
    password character varying(32) NOT NULL,
    lib_name character varying(40) NOT NULL
);


ALTER TABLE public.librarian OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 25774)
-- Name: librarian_lib_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.librarian_lib_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.librarian_lib_id_seq OWNER TO postgres;

--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 207
-- Name: librarian_lib_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.librarian_lib_id_seq OWNED BY public.librarian.lib_id;


--
-- TOC entry 208 (class 1259 OID 25776)
-- Name: location; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.location (
    location_name character varying(40) NOT NULL
);


ALTER TABLE public.location OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 25779)
-- Name: post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.post (
    title text NOT NULL,
    body text NOT NULL,
    create_time timestamp with time zone NOT NULL,
    lib_id integer
);


ALTER TABLE public.post OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 25785)
-- Name: reader; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reader (
    reader_id integer NOT NULL,
    password character varying(32) NOT NULL,
    sex character varying(6) NOT NULL,
    reader_name character varying(40) NOT NULL,
    email character varying(320) NOT NULL,
    tele character varying(20) NOT NULL,
    maxborrow numeric(10,5) NOT NULL,
    longesttime numeric(10,5) NOT NULL,
    deposit numeric(10,5) NOT NULL
);


ALTER TABLE public.reader OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 25788)
-- Name: reader_reader_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reader_reader_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reader_reader_id_seq OWNER TO postgres;

--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 211
-- Name: reader_reader_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reader_reader_id_seq OWNED BY public.reader.reader_id;


--
-- TOC entry 212 (class 1259 OID 25790)
-- Name: record; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.record (
    book_id integer NOT NULL,
    reader_id integer NOT NULL,
    start_time date NOT NULL,
    return_time date,
    current_fine numeric(10,0) NOT NULL
);


ALTER TABLE public.record OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 25793)
-- Name: reserve; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reserve (
    reader_id integer NOT NULL,
    book_id integer NOT NULL,
    start_time timestamp(4) with time zone NOT NULL,
    deadline_time timestamp with time zone NOT NULL
);


ALTER TABLE public.reserve OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 25796)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 2732 (class 2604 OID 25798)
-- Name: admin admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin ALTER COLUMN admin_id SET DEFAULT nextval('public.admin_admin_id_seq'::regclass);


--
-- TOC entry 2733 (class 2604 OID 25799)
-- Name: bookinlib book_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookinlib ALTER COLUMN book_id SET DEFAULT nextval('public.bookinlib_book_id_seq'::regclass);


--
-- TOC entry 2734 (class 2604 OID 25800)
-- Name: income income_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.income ALTER COLUMN income_id SET DEFAULT nextval('public.income_income_id_seq'::regclass);


--
-- TOC entry 2735 (class 2604 OID 25801)
-- Name: librarian lib_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.librarian ALTER COLUMN lib_id SET DEFAULT nextval('public.librarian_lib_id_seq'::regclass);


--
-- TOC entry 2736 (class 2604 OID 25802)
-- Name: reader reader_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reader ALTER COLUMN reader_id SET DEFAULT nextval('public.reader_reader_id_seq'::regclass);


--
-- TOC entry 2884 (class 0 OID 25735)
-- Dependencies: 196
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.admin (admin_id, password, admin_name) VALUES (1, 'root', 'Taiho');


--
-- TOC entry 2886 (class 0 OID 25740)
-- Dependencies: 198
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book (isbn, book_name, book_description, edition, language, publisher_time, price, page, publisher_name, author, catagory) VALUES ('9784040723396', '冴えない彼女の育てかた13', '冴えない彼女との恋物語、ついに完結!', '1.0 version', 'Japanese', '2017-10-20', '50', 278, 'KADOKAWA', '丸戸', 'Novel');
INSERT INTO public.book (isbn, book_name, book_description, edition, language, publisher_time, price, page, publisher_name, author, catagory) VALUES ('9787121194276', '信号与系统', '《国外电子与通信教材系列:信号与系统(第2版)》是美国麻省理工学院（MIT）的经典教材之一，讨论了信号与系统分析的基本理论、基本分析方法及其应用。全书共分11章，主要讲述了线性系统的基本理论、信号与系统的基本概念、线性时不变系统、连续与离散信号的傅里叶表示、傅里叶变换以及时域和频域系统的分析方法等内容。作者使用了大量在滤波、采样、通信和反馈系统中的实例，并行讨论了连续系统、离散系统、时域系统和频域系统的分析方法，使读者能透彻地理解各种信号系统的分析方法并比较其异同。', '2.0 version', 'Chinese', '2013-01-01', '69.00元', 605, '电子工业出版社', '奥本海姆', 'technology');
INSERT INTO public.book (isbn, book_name, book_description, edition, language, publisher_time, price, page, publisher_name, author, catagory) VALUES ('1234567890123', 'java', 'no description', '2.0 version', 'English', '2015-11-01', '69', 409, '机械工业出版社', '奥本海姆', 'technology');
INSERT INTO public.book (isbn, book_name, book_description, edition, language, publisher_time, price, page, publisher_name, author, catagory) VALUES ('9787121087486', '信号与系统', '《信号与系统(第2版)(英文版)》是美国麻省理工学院（MIT）的经典教材之一，书中讨论了信号与系统分析的基本理论、基本分析方法及其应用。全书共分11章，主要讲述了线性系统的基本理论、信号与系统的基本概念、线性时不变系统、连续与离散信号的傅里叶表示、傅里叶变换以及时域和频域系统的分析方法等内容。《信号与系统(第2版)(英文版)》作者使用了大量在滤波、抽样、通信和反馈系统中的实例，并行讨论了连续系统、离散系统、时域系统和频域系统的分析方法，以使读者能透彻地理解各种信号系统的分析方法并比较其异同。', '3.0', 'Chinese', '2012-12-01', '69.00元', 957, '电子工业出版社', '(美)奥本海姆', 'null');


--
-- TOC entry 2887 (class 0 OID 25746)
-- Dependencies: 199
-- Data for Name: bookinlib; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (29, '9787121194276', 'available', '4 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (31, '9787121194276', 'available', '4 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (32, '9787121194276', 'available', '4 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (30, '9787121194276', 'available', '4 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (34, '1234567890123', 'deleted', '4 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (35, '1234567890123', 'deleted', '3 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (37, '1234567890123', 'available', '3 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (36, '1234567890123', 'unavailable', '3 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (38, '9787121194276', 'available', '4 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (39, '9787121194276', 'available', '4 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (40, '9787121194276', 'available', '4 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (41, '9787121194276', 'available', '4 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (42, '9787121194276', 'available', '4 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (33, '1234567890123', 'unavailable', '4 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (27, '9784040723396', 'available', '3 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (26, '9784040723396', 'unavailable', '3 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (43, '1234567890123', 'Lost', '3 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (44, '1234567890123', 'Damage', '3 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (46, '9787121087486', 'available', '1 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (49, '9787121087486', 'available', '3 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (50, '9787121087486', 'available', '3 floor');
INSERT INTO public.bookinlib (book_id, isbn, status, location) VALUES (28, '9784040723396', 'Lost', '3 floor');


--
-- TOC entry 2889 (class 0 OID 25754)
-- Dependencies: 201
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category (category_name) VALUES ('Comic books');
INSERT INTO public.category (category_name) VALUES ('Novel');
INSERT INTO public.category (category_name) VALUES ('Art');


--
-- TOC entry 2890 (class 0 OID 25757)
-- Dependencies: 202
-- Data for Name: default_value; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.default_value (longesttime, fine, deposit, reserve_time) VALUES (30, 1.00000, 300.00000, 2.00000);


--
-- TOC entry 2891 (class 0 OID 25760)
-- Dependencies: 203
-- Data for Name: deletebook; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2892 (class 0 OID 25763)
-- Dependencies: 204
-- Data for Name: income; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2894 (class 0 OID 25771)
-- Dependencies: 206
-- Data for Name: librarian; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.librarian (lib_id, password, lib_name) VALUES (10, '111111', 'lib_master');
INSERT INTO public.librarian (lib_id, password, lib_name) VALUES (7, '123456', 'test_lib');


--
-- TOC entry 2896 (class 0 OID 25776)
-- Dependencies: 208
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.location (location_name) VALUES ('1 floor');
INSERT INTO public.location (location_name) VALUES ('3 floor');
INSERT INTO public.location (location_name) VALUES ('4 floor');
INSERT INTO public.location (location_name) VALUES ('5 floor');


--
-- TOC entry 2897 (class 0 OID 25779)
-- Dependencies: 209
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2898 (class 0 OID 25785)
-- Dependencies: 210
-- Data for Name: reader; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2900 (class 0 OID 25790)
-- Dependencies: 212
-- Data for Name: record; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2901 (class 0 OID 25793)
-- Dependencies: 213
-- Data for Name: reserve; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 197
-- Name: admin_admin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.admin_admin_id_seq', 3, true);


--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 200
-- Name: bookinlib_book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bookinlib_book_id_seq', 53, true);


--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 205
-- Name: income_income_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.income_income_id_seq', 15, true);


--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 207
-- Name: librarian_lib_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.librarian_lib_id_seq', 11, true);


--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 211
-- Name: reader_reader_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reader_reader_id_seq', 17, true);


--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 214
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- TOC entry 2738 (class 2606 OID 25804)
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (admin_id);


--
-- TOC entry 2740 (class 2606 OID 25806)
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (isbn);


--
-- TOC entry 2742 (class 2606 OID 25808)
-- Name: bookinlib bookinlib_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookinlib
    ADD CONSTRAINT bookinlib_pkey PRIMARY KEY (book_id);


--
-- TOC entry 2744 (class 2606 OID 25810)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_name);


--
-- TOC entry 2746 (class 2606 OID 25812)
-- Name: income income_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_pkey PRIMARY KEY (income_id);


--
-- TOC entry 2748 (class 2606 OID 25814)
-- Name: librarian librarian_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.librarian
    ADD CONSTRAINT librarian_pkey PRIMARY KEY (lib_id);


--
-- TOC entry 2750 (class 2606 OID 25816)
-- Name: location location_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.location
    ADD CONSTRAINT location_pkey PRIMARY KEY (location_name);


--
-- TOC entry 2752 (class 2606 OID 25818)
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (title);


--
-- TOC entry 2754 (class 2606 OID 25820)
-- Name: reader reader_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reader
    ADD CONSTRAINT reader_pkey PRIMARY KEY (reader_id);


--
-- TOC entry 2756 (class 2606 OID 25822)
-- Name: reserve reserve_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reserve
    ADD CONSTRAINT reserve_pkey PRIMARY KEY (start_time);


--
-- TOC entry 2761 (class 2606 OID 25823)
-- Name: reserve book_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reserve
    ADD CONSTRAINT book_id FOREIGN KEY (book_id) REFERENCES public.bookinlib(book_id);


--
-- TOC entry 2759 (class 2606 OID 25828)
-- Name: record book_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.record
    ADD CONSTRAINT book_id FOREIGN KEY (book_id) REFERENCES public.bookinlib(book_id);


--
-- TOC entry 2758 (class 2606 OID 25833)
-- Name: deletebook book_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deletebook
    ADD CONSTRAINT book_id FOREIGN KEY (book_id) REFERENCES public.bookinlib(book_id);


--
-- TOC entry 2757 (class 2606 OID 25838)
-- Name: bookinlib isbn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookinlib
    ADD CONSTRAINT isbn FOREIGN KEY (isbn) REFERENCES public.book(isbn);


--
-- TOC entry 2762 (class 2606 OID 25843)
-- Name: reserve reader_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reserve
    ADD CONSTRAINT reader_id FOREIGN KEY (reader_id) REFERENCES public.reader(reader_id);


--
-- TOC entry 2760 (class 2606 OID 25848)
-- Name: record reader_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.record
    ADD CONSTRAINT reader_id FOREIGN KEY (reader_id) REFERENCES public.reader(reader_id);


--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2019-09-22 00:11:21

--
-- PostgreSQL database dump complete
--

