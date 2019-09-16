from django.db import models


class User(models.Model):
    email = models.CharField(max_length=50, blank=True)
    id = models.CharField(max_length=50, null=False, primary_key=True)
    pw = models.CharField(max_length=100, null=False)
    wx_id = models.CharField(max_length=40)
    qq_id = models.CharField(max_length=40)
    name = models.CharField(max_length=20, null=False)
    phone = models.CharField(max_length=20, null=False)
