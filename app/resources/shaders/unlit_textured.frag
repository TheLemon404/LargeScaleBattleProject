#version 330 core

in vec2 pUv;

uniform sampler2D tex;
uniform vec3 albedo;
uniform float alpha;

out vec4 FragColor; // Color output

void main() {
    FragColor = texture(tex, pUv) * vec4(albedo, alpha);
}
