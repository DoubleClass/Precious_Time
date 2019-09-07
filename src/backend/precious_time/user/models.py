from django.db import models


class User(models.Model):
    name = models.CharField(max_length=20, blank=False, primary_key=True)
    pw = models.CharField(max_length=100, blank=False)
