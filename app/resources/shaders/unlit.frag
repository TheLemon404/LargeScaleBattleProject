#version 330 core

in vec2 pUv;

uniform vec3 albedo;
uniform float alpha;

out vec4 FragColor; // Color output

void main() {
    FragColor = vec4(albedo, alpha);
}
