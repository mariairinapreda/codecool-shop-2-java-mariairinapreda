--
-- PostgreSQL database dump
--

-- Dumped from database version 13.5 (Ubuntu 13.5-0ubuntu0.21.04.1)
-- Dumped by pg_dump version 13.5 (Ubuntu 13.5-0ubuntu0.21.04.1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: ioana
--

CREATE TABLE public.categories (
    id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.categories OWNER TO ioana;

--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: ioana
--

CREATE SEQUENCE public.categories_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categories_id_seq OWNER TO ioana;

--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ioana
--

ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id;


--
-- Name: line_items; Type: TABLE; Schema: public; Owner: ioana
--

CREATE TABLE public.line_items (
    id integer NOT NULL,
    quantity integer,
    price text,
    productid integer,
    orderid integer
);


ALTER TABLE public.line_items OWNER TO ioana;

--
-- Name: line_items_id_seq; Type: SEQUENCE; Schema: public; Owner: ioana
--

CREATE SEQUENCE public.line_items_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.line_items_id_seq OWNER TO ioana;

--
-- Name: line_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ioana
--

ALTER SEQUENCE public.line_items_id_seq OWNED BY public.line_items.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: ioana
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    date date,
    status text,
    total_price integer
);


ALTER TABLE public.orders OWNER TO ioana;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: ioana
--

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO ioana;

--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ioana
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: ioana
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name text,
    description text,
    price text,
    currency text,
    categoryid integer,
    supplierid integer
);


ALTER TABLE public.products OWNER TO ioana;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: ioana
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO ioana;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ioana
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: suppliers; Type: TABLE; Schema: public; Owner: ioana
--

CREATE TABLE public.suppliers (
    id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.suppliers OWNER TO ioana;

--
-- Name: suppliers_id_seq; Type: SEQUENCE; Schema: public; Owner: ioana
--

CREATE SEQUENCE public.suppliers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suppliers_id_seq OWNER TO ioana;

--
-- Name: suppliers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ioana
--

ALTER SEQUENCE public.suppliers_id_seq OWNED BY public.suppliers.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: ioana
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name text,
    address text,
    email text,
    phone text,
    password text,
    city text,
    state text,
    zipcode text,
    status text
);


ALTER TABLE public.users OWNER TO ioana;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: ioana
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO ioana;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ioana
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: categories id; Type: DEFAULT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_id_seq'::regclass);


--
-- Name: line_items id; Type: DEFAULT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.line_items ALTER COLUMN id SET DEFAULT nextval('public.line_items_id_seq'::regclass);


--
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: suppliers id; Type: DEFAULT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.suppliers ALTER COLUMN id SET DEFAULT nextval('public.suppliers_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: ioana
--

COPY public.categories (id, name) FROM stdin;
1	Tablet
2	Phone
3	Laptop
\.


--
-- Data for Name: line_items; Type: TABLE DATA; Schema: public; Owner: ioana
--

COPY public.line_items (id, quantity, price, productid, orderid) FROM stdin;
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: ioana
--

COPY public.orders (id, date, status, total_price) FROM stdin;
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: ioana
--

COPY public.products (id, name, description, price, currency, categoryid, supplierid) FROM stdin;
1	Amazon Fire	Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.	49.9	USD	1	1
2	Lenovo IdeaPad Miix 700	Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.	479	USD	1	4
3	Amazon Fire HD 8	Amazons latest Fire HD 8 tablet is a great value for media consumption.	89	USD	1	1
4	IPhone 13	Superbright, Supercolorful, Supersharp.	799.9	USD	2	2
5	tilifon	Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.	49.9	USD	2	2
6	tilifon Fire	Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.	49.9	USD	2	3
7	tableta Fire	Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.	49.9	USD	1	2
8	laptop  a Fire	Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.	49.9	USD	3	1
\.


--
-- Data for Name: suppliers; Type: TABLE DATA; Schema: public; Owner: ioana
--

COPY public.suppliers (id, name) FROM stdin;
1	Amazon
2	Apple
3	Huawei
4	Lenovo
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: ioana
--

COPY public.users (id, name, address, email, phone, password, city, state, zipcode, status) FROM stdin;
18	\N	\N	mariairina.predaeu@gmail.com	\N	e7cd83e7a663d72003d0e9c8c6cfe6b8d8829d0374f025de9e5681e4646dada4409622a51de79e79a3cde5dca67f70570a4cc20b52d676c2baa145383f46139d	\N	\N	\N	SIGNED
19	\N	\N	mariairina.predaeu@gmail.com	\N	e7cd83e7a663d72003d0e9c8c6cfe6b8d8829d0374f025de9e5681e4646dada4409622a51de79e79a3cde5dca67f70570a4cc20b52d676c2baa145383f46139d	\N	\N	\N	UNSIGNED
20	\N	\N	dragosbas@gmail.com	\N	8e11c730c528a0c69211f3f0e04ce4e3da0170787f0d264ae4d19388bbacca95db5f3d0b445c3a796cc23e40426139f7e8cb7776b1bc85f832de93b16b218e36	\N	\N	\N	unsigned
\.


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ioana
--

SELECT pg_catalog.setval('public.categories_id_seq', 3, true);


--
-- Name: line_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ioana
--

SELECT pg_catalog.setval('public.line_items_id_seq', 1, false);


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ioana
--

SELECT pg_catalog.setval('public.orders_id_seq', 1, false);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ioana
--

SELECT pg_catalog.setval('public.products_id_seq', 8, true);


--
-- Name: suppliers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ioana
--

SELECT pg_catalog.setval('public.suppliers_id_seq', 4, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ioana
--

SELECT pg_catalog.setval('public.users_id_seq', 20, true);


--
-- Name: categories categories_pk; Type: CONSTRAINT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pk PRIMARY KEY (id);


--
-- Name: line_items line_items_pk; Type: CONSTRAINT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.line_items
    ADD CONSTRAINT line_items_pk PRIMARY KEY (id);


--
-- Name: orders orders_pk; Type: CONSTRAINT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);


--
-- Name: products products_pk; Type: CONSTRAINT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pk PRIMARY KEY (id);


--
-- Name: suppliers suppliers_pk; Type: CONSTRAINT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.suppliers
    ADD CONSTRAINT suppliers_pk PRIMARY KEY (id);


--
-- Name: users users_pk; Type: CONSTRAINT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- Name: categories_id_uindex; Type: INDEX; Schema: public; Owner: ioana
--

CREATE UNIQUE INDEX categories_id_uindex ON public.categories USING btree (id);


--
-- Name: categories_name_uindex; Type: INDEX; Schema: public; Owner: ioana
--

CREATE UNIQUE INDEX categories_name_uindex ON public.categories USING btree (name);


--
-- Name: line_items_id_uindex; Type: INDEX; Schema: public; Owner: ioana
--

CREATE UNIQUE INDEX line_items_id_uindex ON public.line_items USING btree (id);


--
-- Name: orders_id_uindex; Type: INDEX; Schema: public; Owner: ioana
--

CREATE UNIQUE INDEX orders_id_uindex ON public.orders USING btree (id);


--
-- Name: products_id_uindex; Type: INDEX; Schema: public; Owner: ioana
--

CREATE UNIQUE INDEX products_id_uindex ON public.products USING btree (id);


--
-- Name: suppliers_id_uindex; Type: INDEX; Schema: public; Owner: ioana
--

CREATE UNIQUE INDEX suppliers_id_uindex ON public.suppliers USING btree (id);


--
-- Name: users_id_uindex; Type: INDEX; Schema: public; Owner: ioana
--

CREATE UNIQUE INDEX users_id_uindex ON public.users USING btree (id);


--
-- Name: line_items line_items_orderid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.line_items
    ADD CONSTRAINT line_items_orderid_fkey FOREIGN KEY (orderid) REFERENCES public.orders(id) ON DELETE SET NULL;


--
-- Name: line_items line_items_productid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.line_items
    ADD CONSTRAINT line_items_productid_fkey FOREIGN KEY (productid) REFERENCES public.products(id) ON DELETE SET NULL;


--
-- Name: products products_categoryid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_categoryid_fkey FOREIGN KEY (categoryid) REFERENCES public.categories(id) ON DELETE SET NULL;


--
-- Name: products products_supplierid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ioana
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_supplierid_fkey FOREIGN KEY (supplierid) REFERENCES public.suppliers(id) ON DELETE SET NULL;


--
-- PostgreSQL database dump complete
--

