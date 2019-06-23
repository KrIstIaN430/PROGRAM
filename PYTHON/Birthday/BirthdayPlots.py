from bokeh.plotting import figure, show, output_file
import BirthdayMonths

# we specify an HTML file where the output will go
output_file("plot.html")

# load our x and y data
x = BirthdayMonths.x
y = BirthdayMonths.y

# create a figure
p = figure(x_range=BirthdayMonths.x)

# create a histogram
p.vbar(x=x, top=y, width=0.5)

# render (show) the plot
show(p)