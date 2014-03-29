#ifdef GL_ES
precision mediump float;
#endif

varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform float u_width_ratio;
uniform float u_height_ratio;

void main()
{
	vec2 texCoords = v_texCoords;
	texCoords.x -= mod(texCoords.x, u_width_ratio);
	texCoords.y -= mod(texCoords.y, u_height_ratio);
	gl_FragColor = texture2D(u_texture, texCoords);
}