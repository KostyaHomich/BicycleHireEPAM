--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2019-11-26 13:10:48

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 20616)
-- Name: bicycle_hire; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA bicycle_hire;


ALTER SCHEMA bicycle_hire OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 199 (class 1259 OID 135165)
-- Name: best_bicycle; Type: TABLE; Schema: bicycle_hire; Owner: postgres
--

CREATE TABLE bicycle_hire.best_bicycle (
    id_user bigint NOT NULL,
    id_point_hire_bicycle bigint NOT NULL,
    id integer NOT NULL
);


ALTER TABLE bicycle_hire.best_bicycle OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 135168)
-- Name: best_bicycle_id_seq; Type: SEQUENCE; Schema: bicycle_hire; Owner: postgres
--

CREATE SEQUENCE bicycle_hire.best_bicycle_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bicycle_hire.best_bicycle_id_seq OWNER TO postgres;

--
-- TOC entry 2881 (class 0 OID 0)
-- Dependencies: 200
-- Name: best_bicycle_id_seq; Type: SEQUENCE OWNED BY; Schema: bicycle_hire; Owner: postgres
--

ALTER SEQUENCE bicycle_hire.best_bicycle_id_seq OWNED BY bicycle_hire.best_bicycle.id;


--
-- TOC entry 201 (class 1259 OID 135170)
-- Name: bicycle; Type: TABLE; Schema: bicycle_hire; Owner: postgres
--

CREATE TABLE bicycle_hire.bicycle (
    id bigint NOT NULL,
    link text,
    name text,
    description text
);


ALTER TABLE bicycle_hire.bicycle OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 135176)
-- Name: bicycle_id_seq; Type: SEQUENCE; Schema: bicycle_hire; Owner: postgres
--

CREATE SEQUENCE bicycle_hire.bicycle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bicycle_hire.bicycle_id_seq OWNER TO postgres;

--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 202
-- Name: bicycle_id_seq; Type: SEQUENCE OWNED BY; Schema: bicycle_hire; Owner: postgres
--

ALTER SEQUENCE bicycle_hire.bicycle_id_seq OWNED BY bicycle_hire.bicycle.id;


--
-- TOC entry 203 (class 1259 OID 135178)
-- Name: point_hire; Type: TABLE; Schema: bicycle_hire; Owner: postgres
--

CREATE TABLE bicycle_hire.point_hire (
    id bigint NOT NULL,
    location text,
    telephone text,
    description text
);


ALTER TABLE bicycle_hire.point_hire OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 135184)
-- Name: point_hire_bicycle; Type: TABLE; Schema: bicycle_hire; Owner: postgres
--

CREATE TABLE bicycle_hire.point_hire_bicycle (
    id bigint NOT NULL,
    id_bicycle bigint NOT NULL,
    id_point_hire bigint NOT NULL
);


ALTER TABLE bicycle_hire.point_hire_bicycle OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 135187)
-- Name: point_hire_bicycle_id_seq; Type: SEQUENCE; Schema: bicycle_hire; Owner: postgres
--

CREATE SEQUENCE bicycle_hire.point_hire_bicycle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bicycle_hire.point_hire_bicycle_id_seq OWNER TO postgres;

--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 205
-- Name: point_hire_bicycle_id_seq; Type: SEQUENCE OWNED BY; Schema: bicycle_hire; Owner: postgres
--

ALTER SEQUENCE bicycle_hire.point_hire_bicycle_id_seq OWNED BY bicycle_hire.point_hire_bicycle.id;


--
-- TOC entry 206 (class 1259 OID 135189)
-- Name: point_hire_id_seq; Type: SEQUENCE; Schema: bicycle_hire; Owner: postgres
--

CREATE SEQUENCE bicycle_hire.point_hire_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bicycle_hire.point_hire_id_seq OWNER TO postgres;

--
-- TOC entry 2884 (class 0 OID 0)
-- Dependencies: 206
-- Name: point_hire_id_seq; Type: SEQUENCE OWNED BY; Schema: bicycle_hire; Owner: postgres
--

ALTER SEQUENCE bicycle_hire.point_hire_id_seq OWNED BY bicycle_hire.point_hire.id;


--
-- TOC entry 207 (class 1259 OID 135199)
-- Name: users; Type: TABLE; Schema: bicycle_hire; Owner: postgres
--

CREATE TABLE bicycle_hire.users (
    id bigint NOT NULL,
    login text,
    password text,
    first_name text,
    last_name text,
    email text,
    registration_date text,
    enabled boolean DEFAULT true NOT NULL,
    role text NOT NULL
);


ALTER TABLE bicycle_hire.users OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 135206)
-- Name: users_id_seq; Type: SEQUENCE; Schema: bicycle_hire; Owner: postgres
--

CREATE SEQUENCE bicycle_hire.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bicycle_hire.users_id_seq OWNER TO postgres;

--
-- TOC entry 2885 (class 0 OID 0)
-- Dependencies: 208
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: bicycle_hire; Owner: postgres
--

ALTER SEQUENCE bicycle_hire.users_id_seq OWNED BY bicycle_hire.users.id;


--
-- TOC entry 2736 (class 2604 OID 135208)
-- Name: best_bicycle id; Type: DEFAULT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.best_bicycle ALTER COLUMN id SET DEFAULT nextval('bicycle_hire.best_bicycle_id_seq'::regclass);


--
-- TOC entry 2737 (class 2604 OID 135209)
-- Name: bicycle id; Type: DEFAULT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.bicycle ALTER COLUMN id SET DEFAULT nextval('bicycle_hire.bicycle_id_seq'::regclass);


--
-- TOC entry 2738 (class 2604 OID 135210)
-- Name: point_hire id; Type: DEFAULT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.point_hire ALTER COLUMN id SET DEFAULT nextval('bicycle_hire.point_hire_id_seq'::regclass);


--
-- TOC entry 2739 (class 2604 OID 135211)
-- Name: point_hire_bicycle id; Type: DEFAULT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.point_hire_bicycle ALTER COLUMN id SET DEFAULT nextval('bicycle_hire.point_hire_bicycle_id_seq'::regclass);


--
-- TOC entry 2741 (class 2604 OID 135213)
-- Name: users id; Type: DEFAULT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.users ALTER COLUMN id SET DEFAULT nextval('bicycle_hire.users_id_seq'::regclass);


--
-- TOC entry 2743 (class 2606 OID 135215)
-- Name: bicycle bicycle_pkey; Type: CONSTRAINT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.bicycle
    ADD CONSTRAINT bicycle_pkey PRIMARY KEY (id);


--
-- TOC entry 2747 (class 2606 OID 135217)
-- Name: point_hire_bicycle id_point_hire_bicycle; Type: CONSTRAINT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.point_hire_bicycle
    ADD CONSTRAINT id_point_hire_bicycle PRIMARY KEY (id);


--
-- TOC entry 2749 (class 2606 OID 135219)
-- Name: users login; Type: CONSTRAINT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.users
    ADD CONSTRAINT login UNIQUE (login);


--
-- TOC entry 2745 (class 2606 OID 135221)
-- Name: point_hire point_hire_pkey; Type: CONSTRAINT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.point_hire
    ADD CONSTRAINT point_hire_pkey PRIMARY KEY (id);


--
-- TOC entry 2751 (class 2606 OID 135225)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2753 (class 2606 OID 135226)
-- Name: point_hire_bicycle id_bicycle; Type: FK CONSTRAINT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.point_hire_bicycle
    ADD CONSTRAINT id_bicycle FOREIGN KEY (id_bicycle) REFERENCES bicycle_hire.bicycle(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2754 (class 2606 OID 135231)
-- Name: point_hire_bicycle id_point_hire; Type: FK CONSTRAINT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.point_hire_bicycle
    ADD CONSTRAINT id_point_hire FOREIGN KEY (id_point_hire) REFERENCES bicycle_hire.point_hire(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2752 (class 2606 OID 135236)
-- Name: best_bicycle id_point_hire_bicycle; Type: FK CONSTRAINT; Schema: bicycle_hire; Owner: postgres
--

ALTER TABLE ONLY bicycle_hire.best_bicycle
    ADD CONSTRAINT id_point_hire_bicycle FOREIGN KEY (id_point_hire_bicycle) REFERENCES bicycle_hire.point_hire_bicycle(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2019-11-26 13:10:48

--
-- PostgreSQL database dump complete
--

