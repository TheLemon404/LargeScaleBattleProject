#version 330 core

in vec2 pUv;

uniform sampler2D tex;

out vec4 FragColor; // Color output

void main() {
    FragColor = texture(tex, pUv);
}
