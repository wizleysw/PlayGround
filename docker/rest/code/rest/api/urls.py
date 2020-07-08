from django.urls import path

from api import views

urlpatterns = [
    path('student/<str:name>', views.StudentAPI.as_view()),
    path('student/', views.StudentAPI.as_view()),
    path('student/delete/<str:name>', views.StudentAPI.as_view()),
]