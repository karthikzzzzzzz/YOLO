import streamlit as st
from ultralytics import YOLO
import cv2
import numpy as np
from PIL import Image
import tempfile
import os


model = YOLO('best.pt') 


st.title("Pothole Detection System")
st.write("Upload an image or video to detect potholes")


uploaded_file = st.file_uploader("Upload Image/Video", type=["jpg", "jpeg", "png", "mp4"])

if uploaded_file:
    file_type = uploaded_file.type.split('/')[0]
    
    if file_type == 'image':
        image = Image.open(uploaded_file)
        image_cv = cv2.cvtColor(np.array(image), cv2.COLOR_RGB2BGR)
        results = model(image_cv)
        annotated_frame = results[0].plot()
        st.image(annotated_frame, caption="Detected Potholes", use_column_width=True)
    
    elif file_type == 'video':
        tfile = tempfile.NamedTemporaryFile(delete=False)  
        tfile.write(uploaded_file.read())
        
        cap = cv2.VideoCapture(tfile.name)
        output_frames = []
        while cap.isOpened():
            ret, frame = cap.read()
            if not ret:
                break
            results = model(frame)
            annotated_frame = results[0].plot()
            output_frames.append(annotated_frame)

        cap.release()
        output_path = "output_video.mp4"
        out = cv2.VideoWriter(output_path, cv2.VideoWriter_fourcc(*'mp4v'), 20, 
                              (output_frames[0].shape[1], output_frames[0].shape[0]))

        for frame in output_frames:
            out.write(frame)
        out.release()
        st.video(output_path)

        os.remove(tfile.name)
