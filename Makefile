.PHONY: build test deploy clean

##-- Environment Variables --#

# Docker image information
IMAGE_NAME := http-service-lein
IMAGE_PATH := wdhowe
IMAGE      := $(IMAGE_PATH)/$(IMAGE_NAME)

# Get current branch and transform '/' to '-'
BRANCH := $(or $(CI_COMMIT_REF_NAME), `git rev-parse --abbrev-ref HEAD`)
BRANCH := $(shell echo $(BRANCH) | tr / -)

# Retrieve first 7 characters of current commit hash
SHORT_HASH := `git rev-parse --short HEAD`

# Docker image tag for a local build
BUILD_TAG := $(IMAGE):build

# Docker image tag that will be pushed to the registry
TAG := $(IMAGE):$(BRANCH)-$(SHORT_HASH)

##-- Main Makefile Targets --##

# build - build a jar, local docker image, and tag the image
build: docker-build docker-tag

# test - run unit tests
test:
	lein test

# deploy - push image into registry
deploy: docker-login docker-push

##-- Extra Makefile Targets --##

# clean - cleanup project space
clean:
	lein clean

# shell - open a shell on the build container
shell:
	docker run --detach --rm --publish 8080:8080 --name testing $(BUILD_TAG) &&\
	docker exec --interactive --tty testing /bin/bash &&\
	docker stop testing

##-- Lein Targets --##

# lein-build - build the project uberjar
lein-build:
	lein deps
	lein uberjar

# lein-deploy - deploy the project to clojars
lein-deploy: lein-build test
	lein deploy clojars

##-- Docker Helper Targets --##

# docker-build - build an image with a local build tag
docker-build:
	docker build --tag $(BUILD_TAG) --rm --compress $(PWD)

# docker-tag - prep local built image with tag for pushing to registry
docker-tag:
	docker tag $(BUILD_TAG) $(TAG)

# docker-run - run the container interactively
docker-run:
	docker run --interactive --tty --rm --publish 8080:8080 --name testing $(BUILD_TAG)

# docker-run-detach - run the container detached
docker-run-detach:
	docker run --detach --rm --publish 8080:8080 --name testing $(BUILD_TAG)

# docker-login - login to registry using vars if available,
#                otherwise interactive login.
docker-login:
	@$(if $(and $(CI_REGISTRY_USER), $(CI_REGISTRY_PASSWORD)), \
		docker login -u $(CI_REGISTRY_USER) -p $(CI_REGISTRY_PASSWORD), \
		docker login)

# docker-push - push image to registry
docker-push:
	docker push $(TAG)

##-- Debug --##

# debug - display all environment variables
debug:
	@echo "IMAGE:     $(IMAGE)"
	@echo "BUILD_TAG: $(BUILD_TAG)"
	@echo "TAG:       $(TAG)"
