FROM ubuntu:latest
LABEL authors="owner"

ENTRYPOINT ["top", "-b"]