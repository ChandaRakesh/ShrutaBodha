docker compose -f docker/docker-compose.yml up -d   > to make containers up in the compose file.
docker compose -f docker/docker-compose.yml down > to make containers down using compose file without losing the tables and data in the db.







docker exec -it shrutabodha-localstack awslocal s3 mb s3://shrutabodha-videos executed this command inside the container to create bucket.

docker exec → run command inside container
shrutabodha-localstack → your LocalStack container
awslocal → LocalStack wrapper for AWS CLI
s3 mb → make bucket

docker exec -it shrutabodha-localstack awslocal s3 ls this command is to verify the bucket.





***
LEARN MORE ABOUT INDEXES
CREATE INDEX idx_videos_user_id ON videos(user_id);
CREATE INDEX idx_chunks_video_id ON video_chunks(video_id);


CREATE INDEX idx_video_chunks_embedding
ON video_chunks
USING ivfflat (embedding vector_cosine_ops);

this indexes are created after the creation of tables this needs to be studied I dont want to do it right now parking side.
***