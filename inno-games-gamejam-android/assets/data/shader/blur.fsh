#ifdef GL_ES
precision mediump float;
#endif

varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform vec2 u_radial_origin;
uniform vec2 u_radial_size;
uniform float u_radial_blur;
uniform float u_radial_bright;

void main() {
	vec4 sumColor = vec4(0.0, 0.0, 0.0, 0.0);
	vec2 texCoords = v_texCoords;
	texCoords += u_radial_size * 0.5 - u_radial_origin;
	for (int i = 0; i < 12; i++) {
  		float scale = 1.0 - u_radial_blur * (float(i) / 11.0);
  		sumColor += texture2D(u_texture, texCoords * scale + u_radial_origin);
  	}
  	gl_FragColor = sumColor / 12.0 * u_radial_bright;
}