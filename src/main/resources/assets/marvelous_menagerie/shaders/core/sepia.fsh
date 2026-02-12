#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;

in float vertexDistance;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord0) * ColorModulator;
    if (color.a < 0.1) {
        discard;
    }
    float fade = linear_fog_fade(vertexDistance, FogStart, FogEnd)*0.85;
    float fade2 = linear_fog_fade(vertexDistance, FogStart, FogEnd)*0.95;
    float fade3 = linear_fog_fade(vertexDistance, FogStart, FogEnd)*0.9;

    float rr = .3;
    float rg = .7;
    float rb = .2;
    float ra = 0.17;

    float gr = .3;
    float gg = .6;
    float gb = .1;
    float ga = 0.17;

    float br = .2;
    float bg = .5;
    float bb = .1;
    float ba = 0.15;

    float red = (rr * color.r) + (rb * color.b) + (rg * color.g) + (ra * color.a);
    float green = (gr * color.r) + (gb * color.b) + (gg * color.g) + (ga * color.a);
    float blue = (br * color.r) + (bb * color.b) + (bg * color.g) + (ba * color.a);

    fragColor = vec4(red * fade2, green * fade3, blue * fade, color.a);
}