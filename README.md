# hunnid_blog

#DB design
https://dbdiagram.io/d/63f04410296d97641d81e62f

#Document
http://localhost:8089/api/v1/swagger-ui/index.html

http://localhost:8089/api/v1/oauth2/authorization/google


## change tag before pushing images
docker tag blog-api hungnguyenb/blog-api
docker build -t blog-api .
docker run -d --name=blog-api -p 8089:8089 blog-api