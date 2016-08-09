SELECT img.name, txt.annotation "text", qry.annotation "query", tag.annotation "tags", ass.annotation "assessment_annotation", ass.relevance "assessment_relevance" FROM images img
LEFT JOIN annotated_text_images txt ON txt.image_id = img.id
LEFT JOIN annotated_query_images qry ON qry.image_id = img.id
LEFT JOIN annotated_tag_images tag ON tag.image_id = img.id
LEFT JOIN (
  SELECT
  DISTINCT
  image_id,
  array(
      SELECT
      annotation
    FROM annotated_assessment_images
  WHERE image_id = 70
  ) annotation,
  array(
    SELECT
      relevance
    FROM annotated_assessment_images
    WHERE image_id = 70
  ) relevance
  FROM annotated_assessment_images
  ) ass ON ass.image_id = img.id
WHERE img.id = 70;