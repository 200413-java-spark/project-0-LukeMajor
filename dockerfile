FROM postgres
ENV POSTGRES_USER luke
ENV POSTGRES_PASSWORD luke
ADD init.sql /docker-entrypoint-initdb.d
EXPOSE 5432