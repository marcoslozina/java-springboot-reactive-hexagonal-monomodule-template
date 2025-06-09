provider "aws" {
  region = "us-east-1"
}

resource "aws_sqs_queue" "event_queue" {
  name = "event-driven-queue"
}