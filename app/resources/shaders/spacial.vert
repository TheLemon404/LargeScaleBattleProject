#version 330 core

layout(location = 0) in vec3 aPos; // Vertex position input
layout(location = 1) in vec3 aNorm;
layout(location = 2) in vec2 aUv;

uniform mat4 transform;
uniform mat4 view;
uniform mat4 projection;

out vec2 pUv;

void main() {
    gl_Position = projection * view * transform * vec4(aPos, 1.0);
    pUv = aUv;
}
