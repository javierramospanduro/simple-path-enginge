--
-- PostgreSQL database dump
--

-- Dumped from database version 11.10 (Debian 11.10-1.pgdg90+1)
-- Dumped by pg_dump version 13.0

-- Started on 2020-11-21 18:47:33

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
-- TOC entry 7 (class 2615 OID 17057)
-- Name: operation; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA operation;


ALTER SCHEMA operation OWNER TO postgres;

--
-- TOC entry 10 (class 2615 OID 17056)
-- Name: param; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA param;


ALTER SCHEMA param OWNER TO postgres;

--
-- TOC entry 9 (class 2615 OID 17055)
-- Name: users; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA users;


ALTER SCHEMA users OWNER TO postgres;

SET default_tablespace = '';

--
-- TOC entry 209 (class 1259 OID 17138)
-- Name: operation; Type: TABLE; Schema: operation; Owner: postgres
--

CREATE TABLE operation.operation (
    operation_key character varying NOT NULL,
    formula_key character varying,
    formula character varying,
    applied_formula character varying,
    operation_relevant_info character varying,
    audit_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    audit_user character varying(26) NOT NULL
);


ALTER TABLE operation.operation OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 17064)
-- Name: axis; Type: TABLE; Schema: param; Owner: postgres
--

CREATE TABLE param.axis (
    axis_id character varying(8) NOT NULL,
    axis_des character varying(20),
    type_id character varying(8),
    is_valid boolean DEFAULT true NOT NULL,
    audit_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    audit_user character varying(26) NOT NULL
);


ALTER TABLE param.axis OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 17078)
-- Name: axis_possible_values; Type: TABLE; Schema: param; Owner: postgres
--

CREATE TABLE param.axis_possible_values (
    axis_id character varying(8) NOT NULL,
    type_id character varying(8) NOT NULL,
    value_id integer NOT NULL,
    is_valid boolean DEFAULT true NOT NULL,
    boolean_value boolean,
    numeric_value numeric,
    string_value character varying,
    audit_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    audit_user character varying(26) NOT NULL
);


ALTER TABLE param.axis_possible_values OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 17076)
-- Name: axis_possible_values_value_id_seq; Type: SEQUENCE; Schema: param; Owner: postgres
--

CREATE SEQUENCE param.axis_possible_values_value_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE param.axis_possible_values_value_id_seq OWNER TO postgres;

--
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 201
-- Name: axis_possible_values_value_id_seq; Type: SEQUENCE OWNED BY; Schema: param; Owner: postgres
--

ALTER SEQUENCE param.axis_possible_values_value_id_seq OWNED BY param.axis_possible_values.value_id;


--
-- TOC entry 199 (class 1259 OID 17058)
-- Name: axis_types; Type: TABLE; Schema: param; Owner: postgres
--

CREATE TABLE param.axis_types (
    type_id character varying(8) NOT NULL,
    type_desc character varying(50) NOT NULL,
    audit_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    audit_user character varying(26) NOT NULL
);


ALTER TABLE param.axis_types OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 17102)
-- Name: formulas; Type: TABLE; Schema: param; Owner: postgres
--

CREATE TABLE param.formulas (
    formula_id integer NOT NULL,
    formula_code character varying,
    formula_key character varying,
    is_valid boolean DEFAULT true,
    formula character varying NOT NULL,
    audit_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    audit_user character varying(26) NOT NULL
);


ALTER TABLE param.formulas OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 17100)
-- Name: formulas_formula_id_seq; Type: SEQUENCE; Schema: param; Owner: postgres
--

CREATE SEQUENCE param.formulas_formula_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE param.formulas_formula_id_seq OWNER TO postgres;

--
-- TOC entry 2959 (class 0 OID 0)
-- Dependencies: 204
-- Name: formulas_formula_id_seq; Type: SEQUENCE OWNED BY; Schema: param; Owner: postgres
--

ALTER SEQUENCE param.formulas_formula_id_seq OWNED BY param.formulas.formula_id;


--
-- TOC entry 206 (class 1259 OID 17115)
-- Name: literals; Type: TABLE; Schema: param; Owner: postgres
--

CREATE TABLE param.literals (
    literal_code character varying(26) NOT NULL,
    literal_type character varying(26) NOT NULL,
    literal_value character varying(255) NOT NULL,
    audit_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    audit_user character varying(26) NOT NULL
);


ALTER TABLE param.literals OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 17094)
-- Name: operators; Type: TABLE; Schema: param; Owner: postgres
--

CREATE TABLE param.operators (
    operator_id character varying(26) NOT NULL,
    operator_des character varying(50) NOT NULL,
    audit_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    audit_user character varying(26) NOT NULL
);


ALTER TABLE param.operators OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 17121)
-- Name: roles; Type: TABLE; Schema: users; Owner: postgres
--

CREATE TABLE users.roles (
    role_id character varying(8) NOT NULL,
    role_description character varying(30) NOT NULL,
    audit_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    audit_user character varying(26) NOT NULL
);


ALTER TABLE users.roles OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 17127)
-- Name: users; Type: TABLE; Schema: users; Owner: postgres
--

CREATE TABLE users.users (
    user_id character varying(8) NOT NULL,
    role_id character varying(8) NOT NULL,
    creation_date date DEFAULT CURRENT_DATE NOT NULL,
    expiration_date date,
    name character varying(255) NOT NULL
);


ALTER TABLE users.users OWNER TO postgres;

--
-- TOC entry 2783 (class 2604 OID 17081)
-- Name: axis_possible_values value_id; Type: DEFAULT; Schema: param; Owner: postgres
--

ALTER TABLE ONLY param.axis_possible_values ALTER COLUMN value_id SET DEFAULT nextval('param.axis_possible_values_value_id_seq'::regclass);


--
-- TOC entry 2787 (class 2604 OID 17105)
-- Name: formulas formula_id; Type: DEFAULT; Schema: param; Owner: postgres
--

ALTER TABLE ONLY param.formulas ALTER COLUMN formula_id SET DEFAULT nextval('param.formulas_formula_id_seq'::regclass);


--
-- TOC entry 2949 (class 0 OID 17138)
-- Dependencies: 209
-- Data for Name: operation; Type: TABLE DATA; Schema: operation; Owner: postgres
--

COPY operation.operation (operation_key, formula_key, formula, applied_formula, operation_relevant_info, audit_date, audit_user) FROM stdin;
\.


--
-- TOC entry 2940 (class 0 OID 17064)
-- Dependencies: 200
-- Data for Name: axis; Type: TABLE DATA; Schema: param; Owner: postgres
--

COPY param.axis (axis_id, axis_des, type_id, is_valid, audit_date, audit_user) FROM stdin;
\.


--
-- TOC entry 2942 (class 0 OID 17078)
-- Dependencies: 202
-- Data for Name: axis_possible_values; Type: TABLE DATA; Schema: param; Owner: postgres
--

COPY param.axis_possible_values (axis_id, type_id, value_id, is_valid, boolean_value, numeric_value, string_value, audit_date, audit_user) FROM stdin;
\.


--
-- TOC entry 2939 (class 0 OID 17058)
-- Dependencies: 199
-- Data for Name: axis_types; Type: TABLE DATA; Schema: param; Owner: postgres
--

COPY param.axis_types (type_id, type_desc, audit_date, audit_user) FROM stdin;
\.


--
-- TOC entry 2945 (class 0 OID 17102)
-- Dependencies: 205
-- Data for Name: formulas; Type: TABLE DATA; Schema: param; Owner: postgres
--

COPY param.formulas (formula_id, formula_code, formula_key, is_valid, formula, audit_date, audit_user) FROM stdin;
\.


--
-- TOC entry 2946 (class 0 OID 17115)
-- Dependencies: 206
-- Data for Name: literals; Type: TABLE DATA; Schema: param; Owner: postgres
--

COPY param.literals (literal_code, literal_type, literal_value, audit_date, audit_user) FROM stdin;
\.


--
-- TOC entry 2943 (class 0 OID 17094)
-- Dependencies: 203
-- Data for Name: operators; Type: TABLE DATA; Schema: param; Owner: postgres
--

COPY param.operators (operator_id, operator_des, audit_date, audit_user) FROM stdin;
EQUALS	EXACT VALUE	2020-11-21 17:43:41.428814	INITIAL
GREAT	GREAT THAN	2020-11-21 17:43:41.428814	INITIAL
LESS	LESS THAN	2020-11-21 17:43:41.428814	INITIAL
GREATOREQUAL	GREAT OR EQUAL THAN	2020-11-21 17:43:41.428814	INITIAL
LESSTOREQUAL	LESS OR EQUAL THAN	2020-11-21 17:43:41.428814	INITIAL
BETWEEN	BETWEEN TWO VALUES	2020-11-21 17:43:41.428814	INITIAL
\.


--
-- TOC entry 2947 (class 0 OID 17121)
-- Dependencies: 207
-- Data for Name: roles; Type: TABLE DATA; Schema: users; Owner: postgres
--

COPY users.roles (role_id, role_description, audit_date, audit_user) FROM stdin;
\.


--
-- TOC entry 2948 (class 0 OID 17127)
-- Dependencies: 208
-- Data for Name: users; Type: TABLE DATA; Schema: users; Owner: postgres
--

COPY users.users (user_id, role_id, creation_date, expiration_date, name) FROM stdin;
\.


--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 201
-- Name: axis_possible_values_value_id_seq; Type: SEQUENCE SET; Schema: param; Owner: postgres
--

SELECT pg_catalog.setval('param.axis_possible_values_value_id_seq', 1, false);


--
-- TOC entry 2961 (class 0 OID 0)
-- Dependencies: 204
-- Name: formulas_formula_id_seq; Type: SEQUENCE SET; Schema: param; Owner: postgres
--

SELECT pg_catalog.setval('param.formulas_formula_id_seq', 1, false);


--
-- TOC entry 2813 (class 2606 OID 17146)
-- Name: operation pk_operation; Type: CONSTRAINT; Schema: operation; Owner: postgres
--

ALTER TABLE ONLY operation.operation
    ADD CONSTRAINT pk_operation PRIMARY KEY (operation_key);


--
-- TOC entry 2797 (class 2606 OID 17070)
-- Name: axis pk_axis; Type: CONSTRAINT; Schema: param; Owner: postgres
--

ALTER TABLE ONLY param.axis
    ADD CONSTRAINT pk_axis PRIMARY KEY (axis_id);


--
-- TOC entry 2799 (class 2606 OID 17088)
-- Name: axis_possible_values pk_axis_possible_values; Type: CONSTRAINT; Schema: param; Owner: postgres
--

ALTER TABLE ONLY param.axis_possible_values
    ADD CONSTRAINT pk_axis_possible_values PRIMARY KEY (axis_id, value_id);


--
-- TOC entry 2795 (class 2606 OID 17063)
-- Name: axis_types pk_axis_type; Type: CONSTRAINT; Schema: param; Owner: postgres
--

ALTER TABLE ONLY param.axis_types
    ADD CONSTRAINT pk_axis_type PRIMARY KEY (type_id);


--
-- TOC entry 2803 (class 2606 OID 17112)
-- Name: formulas pk_formulas; Type: CONSTRAINT; Schema: param; Owner: postgres
--

ALTER TABLE ONLY param.formulas
    ADD CONSTRAINT pk_formulas PRIMARY KEY (formula_id);


--
-- TOC entry 2807 (class 2606 OID 17120)
-- Name: literals pk_literals; Type: CONSTRAINT; Schema: param; Owner: postgres
--

ALTER TABLE ONLY param.literals
    ADD CONSTRAINT pk_literals PRIMARY KEY (literal_code);


--
-- TOC entry 2801 (class 2606 OID 17099)
-- Name: operators pk_operators; Type: CONSTRAINT; Schema: param; Owner: postgres
--

ALTER TABLE ONLY param.operators
    ADD CONSTRAINT pk_operators PRIMARY KEY (operator_id);


--
-- TOC entry 2809 (class 2606 OID 17126)
-- Name: roles pk_roles; Type: CONSTRAINT; Schema: users; Owner: postgres
--

ALTER TABLE ONLY users.roles
    ADD CONSTRAINT pk_roles PRIMARY KEY (role_id);


--
-- TOC entry 2811 (class 2606 OID 17132)
-- Name: users pk_users; Type: CONSTRAINT; Schema: users; Owner: postgres
--

ALTER TABLE ONLY users.users
    ADD CONSTRAINT pk_users PRIMARY KEY (user_id);


--
-- TOC entry 2804 (class 1259 OID 17113)
-- Name: ui_param_formulas_code; Type: INDEX; Schema: param; Owner: postgres
--

CREATE UNIQUE INDEX ui_param_formulas_code ON param.formulas USING btree (formula_code);


--
-- TOC entry 2805 (class 1259 OID 17114)
-- Name: ui_param_formulas_key; Type: INDEX; Schema: param; Owner: postgres
--

CREATE UNIQUE INDEX ui_param_formulas_key ON param.formulas USING btree (formula_key);


--
-- TOC entry 2817 (class 2606 OID 17147)
-- Name: operation fk_operation_formula; Type: FK CONSTRAINT; Schema: operation; Owner: postgres
--

ALTER TABLE ONLY operation.operation
    ADD CONSTRAINT fk_operation_formula FOREIGN KEY (formula_key) REFERENCES param.formulas(formula_key);


--
-- TOC entry 2815 (class 2606 OID 17089)
-- Name: axis_possible_values fk_axis_axis_possible_values; Type: FK CONSTRAINT; Schema: param; Owner: postgres
--

ALTER TABLE ONLY param.axis_possible_values
    ADD CONSTRAINT fk_axis_axis_possible_values FOREIGN KEY (axis_id) REFERENCES param.axis(axis_id);


--
-- TOC entry 2814 (class 2606 OID 17071)
-- Name: axis fk_axis_axis_types; Type: FK CONSTRAINT; Schema: param; Owner: postgres
--

ALTER TABLE ONLY param.axis
    ADD CONSTRAINT fk_axis_axis_types FOREIGN KEY (type_id) REFERENCES param.axis_types(type_id);


--
-- TOC entry 2816 (class 2606 OID 17133)
-- Name: users fk_users_roles; Type: FK CONSTRAINT; Schema: users; Owner: postgres
--

ALTER TABLE ONLY users.users
    ADD CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES users.roles(role_id);


--
-- TOC entry 2955 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA operation; Type: ACL; Schema: -; Owner: postgres
--

GRANT USAGE ON SCHEMA operation TO PUBLIC;


--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 10
-- Name: SCHEMA param; Type: ACL; Schema: -; Owner: postgres
--

GRANT USAGE ON SCHEMA param TO PUBLIC;


--
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 9
-- Name: SCHEMA users; Type: ACL; Schema: -; Owner: postgres
--

GRANT USAGE ON SCHEMA users TO PUBLIC;


-- Completed on 2020-11-21 18:47:33

--
-- PostgreSQL database dump complete
--

