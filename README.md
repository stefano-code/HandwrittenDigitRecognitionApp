# ✍️ Handwritten Digit Recognition — Android (TensorFlow Lite)

This project is a simple Android application that can recognize handwritten digits.

You can draw a number on the screen…  
and the app will try to understand what you wrote.

All the intelligence runs directly on the device.  
👉 No internet connection is needed.

This is a small but real example of **AI on Edge** 🚀


---

# 📱 What This App Does

- You draw a digit (0–9) on the screen  
- The app converts your drawing into a 28x28 image  
- A neural network processes the image  
- The predicted digit is shown on the screen  

Simple. Fast. Local.


---

# 🧠 The Model

The model is based on the **MNIST dataset**, a classic dataset for handwritten digits.

It is a simple neural network:

- Input: 28 × 28 image → flattened to 784 values  
- Hidden layer: 512 neurons (ReLU)  
- Output: 10 classes (Softmax)  

The model was trained using **Keras (Python)** and then converted to **TensorFlow Lite**.


---

# ⚡ Two Models Included

Inside the app (`assets/` folder), you will find two models:

- `digit_recognition.tflite` → original model (float32)  
- `digit_recognition_quant.tflite` → quantized model (dynamic quantization)  

👉 The quantized model is:
- smaller  
- faster  
- more efficient  

👉 The original model is:
- slightly more accurate  
- easier to understand  


---

# 🔄 How to Switch Model

You can easily choose which model to use.

Just change the file name when loading the model in your code:

```kotlin
val modelPath = "digit_recognition.tflite"
```
or
```kotlin
val modelPath = "digit_recognition_quant.tflite"
```

That's it.

Same app. Different model.

---

# 🛠️ Tech Stack

- Kotlin
- Android SDK
- TensorFlow Lite
- Keras (for training, in Python)

---

# 🧪 How It Works (Simple View)

1. You draw on a canvas
2. The drawing is converted to a bitmap
3. The bitmap is resized to 28x28
4. Pixel values are normalized
5. The model runs inference
6. The app shows the predicted digit

---

# 📦 Why Quantization Matters

The quantized model uses less memory and runs faster.

This is very important on mobile devices because:

- memory is limited
- CPU is limited
- battery matters

👉 This is a key idea in Edge AI

---

# 🚀 AI on Edge

In this project:

- The model is trained on a computer
- Then deployed on a smartphone

This is the core idea of:

👉 **AI on Edge**

No cloud.  
No delay.  
Just real-time intelligence on your device.

---

# 📚 Related Work

This project is part of a series about AI on Edge.

We start from simple models…  
and step by step we bring them to real devices.


---

# 🙌 Final Thought

This app may look simple.

But behind it, there is something powerful:

👉 a machine that learns  
👉 a model that runs locally  
👉 intelligence inside a device

And this is just the beginning 🚀
