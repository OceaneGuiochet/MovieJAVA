-- ============================================
-- Styles
-- ============================================
INSERT INTO
    style (style_name)
VALUES ('SF'),
    ('DRAME'),
    ('THRILLER'),
    ('ACTION');

-- ============================================
-- Productors
-- ============================================
INSERT INTO
    productor (name)
VALUES ('Warner Bros'),
    ('Universal Pictures'),
    ('Paramount Pictures');

-- ============================================
-- Movies
-- ============================================
INSERT INTO
    movie (
        title,
        production_year,
        style_id,
        produced_by
    )
VALUES ('Cloud Atlas', 2012, 1, 1),
    ('Shutter Island', 2010, 3, 2),
    ('Interstellar', 2018, 1, 1),
    ('Pulp Fiction', 2001, 4, 3),
    (
        'Mulholland Drive',
        2001,
        3,
        2
    );

-- ============================================
-- Actors
-- ============================================
INSERT INTO
    actor (
        last_name,
        first_name,
        birth_date
    )
VALUES (
        'DiCaprio',
        'Leonardo',
        '1974-11-11'
    ),
    (
        'Johansson',
        'Scarlett',
        '1984-11-22'
    ),
    ('Pitt', 'Brad', '1963-12-18');

-- ============================================
-- Roles
-- ============================================
INSERT INTO
    role (role_name, actor_id, movie_id)
VALUES ('Teddy Daniels', 1, 2),
    ('Amelia Brand', 2, 3),
    ('Vincent Vega', 3, 4);

-- ============================================
-- Customers
-- ============================================
INSERT INTO customer (name) VALUES ('Alice'), ('Bob'), ('Charlie');

-- ============================================
-- Borrows
-- ============================================
INSERT INTO
    borrow (
        date,
        status,
        customer_id,
        movie_id
    )
VALUES (
        '2025-11-01',
        'BORROWED',
        1,
        1
    ),
    (
        '2025-11-02',
        'RETURNED',
        2,
        2
    ),
    (
        '2025-11-03',
        'BORROWED',
        3,
        3
    );