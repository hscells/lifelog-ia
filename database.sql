DROP TABLE IF EXISTS images,chunks,people,annotated_images CASCADE;

-- images within a chunk are able to be annotated
CREATE TABLE images (
  id  SERIAL,
  name VARCHAR(255),
  data TEXT,
  annotated BOOL,
  chunk_id INT
);

-- the annotated here refers to the chunk as a whole to be annotated
CREATE TABLE chunks (
  id SERIAL,
  annotated BOOL
);

-- a person is used to identify who annotated an image
CREATE TABLE people (
  id SERIAL,
  name VARCHAR(255)
);

-- when images get annotated, they are added to this table
CREATE TABLE annotated_images (
  id SERIAL,
  image_id INT,
  person_id INT,
  annotation TEXT
);
